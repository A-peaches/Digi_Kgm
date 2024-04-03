package digidigi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class UserWindow extends JFrame implements ActionListener {
	private User thisUser;
	private JList<ChatRoom> chatRoomList;
	DefaultListModel<ChatRoom> chatListModel;
	private JButton btnChatList, btnAddChat, btnSet;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private String sql;
	private String roomName;
	private int roomNum;
	private JLabel profile;
	private byte[] imageData;
	Notice notice;
	PreparedStatement pstmt;
	Connection conn;
	

	public UserWindow(User user) {
		thisUser = user;
		// 채팅방 목록 창.
		setTitle("digidigi Talk");
		//창크기
		setSize(400, 550);
		setLocationRelativeTo(null); // 화면 중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JVM에서 완전한 창닫기. 닫기버튼 클릭시 창 종료.
		chatListModel = new DefaultListModel<>();
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(Color.WHITE);
		
		JPanel chatListPanel = new JPanel();
		JPanel addChatPanel = new JPanel();
		JPanel settingPanel = new JPanel();

		
	    ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    setIconImage(icon.getImage());
		// 채팅방추가패널

		setupChatListPanel(chatListPanel);
		setupAddChatPanel(addChatPanel);
		setupSettingPanel(settingPanel);

		cardPanel.add(chatListPanel, "ChatList");
		cardPanel.add(addChatPanel, "AddChat");
		cardPanel.add(settingPanel, "Setting");

		add(cardPanel, BorderLayout.CENTER);

	}

	private void setupAddChatPanel(JPanel addChatPanel) {

		// 패널레이아웃설정
		addChatPanel.setLayout(new BorderLayout(10, 10));
		addChatPanel.setBackground(Color.WHITE);
		
		// 채팅방 만들기 라벨
		JLabel title = new JLabel(" ♥ New Chatting Room ♥ ");
		title.setFont(title.getFont().deriveFont(20f));
		addChatPanel.add(title, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		// '채팅방에 초대하실 아이디를 입력해주세요.' 라벨
		JPanel inputAndInvitePanel = new JPanel(new BorderLayout());
		JLabel inviteLabel = new JLabel("Please enter the ID you want to invite to the chat room.",
				SwingConstants.CENTER);
		inviteLabel.setFont(inviteLabel.getFont().deriveFont(14f));
		inputAndInvitePanel.add(inviteLabel, BorderLayout.NORTH);
		inputAndInvitePanel.setBackground(Color.WHITE);
		// ID입력 추가 부분

		// 채팅방 이름 입력 필드
		JPanel inputPanel = new JPanel(new FlowLayout());
		JTextField RoomNameField = new JTextField(20);
		RoomNameField.setFont(RoomNameField.getFont().deriveFont(16f));
		inputPanel.add(RoomNameField);
		inputPanel.setBackground(Color.WHITE);
		
		// ID추가 버튼
		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/css/bigadd2.png"));
		JButton addBtn = new JButton(addIcon);
		addBtn.setContentAreaFilled(false); //기존버튼디자인 제거 
		addBtn
		.setBorderPainted(false); // 기존버튼디자인 제거
		inputPanel.add(addBtn);

		inputAndInvitePanel.add(inputPanel, BorderLayout.SOUTH);

		// 입력 패널을 중앙 패널의 NORTH에 추가
		centerPanel.add(inputAndInvitePanel, BorderLayout.NORTH);

		// 입력된 ID들을 보여줄 리스트와 스크롤 팬
		DefaultListModel<String> listModel = new DefaultListModel<>();

		JList<String> idList = new JList<>(listModel);
		JScrollPane listScrollPane = new JScrollPane(idList);

		// 리스트 스크롤 팬을 중앙 패널의 CENTER에 추가
		centerPanel.add(listScrollPane, BorderLayout.CENTER);

		// 중앙 패널을 addChatPanel의 CENTER에 추가
		addChatPanel.add(centerPanel, BorderLayout.CENTER);

		// 버튼 패널
		JPanel btnPanel = new JPanel(new FlowLayout());
		btnPanel.setBackground(Color.WHITE);
		// 생성 버튼
		
		ImageIcon cerateIcon = new ImageIcon(getClass().getResource("/css/Create.png"));
		JButton createBtn = new JButton(cerateIcon);
		createBtn.setContentAreaFilled(false); //기존버튼디자인 제거 
		createBtn.setBorderPainted(false); // 기존버튼디자인 제거
		btnPanel.add(createBtn);
		// 취소 버튼

		addChatPanel.add(btnPanel, BorderLayout.SOUTH);

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = RoomNameField.getText();
				if (!id.isEmpty()) {
					listModel.addElement(id);
					RoomNameField.setText("");
				}
			}
		});

		//채팅방 생성.
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// thisUser를 방장으로 room을 생성하면서, id만큼 멤버등록.
				roomName = thisUser.getId()+",";
				for (int i = 0; i < listModel.size(); i++) {
					roomName += listModel.get(i)+",";
				}
				
				conn = DbConnect.getConn().getDb();
				sql = "insert into room (room_name) values (?)";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, roomName);
					pstmt.executeUpdate();//쿼리문실행
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}// 방정보 추가
					
				//방금 생 성된 room_num 갖고오기...
				sql = "select room_num from room where room_name = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, roomName);
					ResultSet rs = pstmt.executeQuery();
					
					//chatRoomList - CHATROOM
					while (rs.next()) {
				        roomNum = rs.getInt(1); // 컬럼 이름으로 데이터 가져오기

					}
				    
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//room_member 추가.
				System.out.println(roomNum);
				System.out.println(listModel.get(0));
				for (int i = 0; i < listModel.size(); i++) {
					sql = "insert into room_member (room_num, id) values (?, ?);";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, roomNum);
						pstmt.setString(2, listModel.get(i));
						pstmt.executeUpdate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				//방장 추가.
				sql = "insert into room_member (room_num, id, manager) values (?, ?, ?);";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, roomNum);
					pstmt.setString(2, thisUser.getId());
					pstmt.setBoolean(3, true);
					pstmt.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//초기화
				listModel.clear();
				chatListModel.clear();
				//ChatList로 돌아가기.
				getRoomList();
				cardLayout.show(cardPanel, "ChatList");
				btnEnabled(false, true, true);
			}
		});



	}

	private void setupSettingPanel(JPanel settingPanel) {
		settingPanel.setLayout(null);
		

		//Join 텍스트 생성.
		JLabel setLabel = new JLabel("Setting");
		setLabel.setBounds(159,50,80,25);
		//setBounds(x, y, w, h) x좌표, y좌표, 가로,세로 크기
		setLabel.setFont(setLabel.getFont().deriveFont(20f));
		settingPanel.add(setLabel);
		settingPanel.setBackground(Color.WHITE);
		//닉네임 라벨
		JLabel nickLabel = new JLabel("your nickName :" + thisUser.getNickName());
		nickLabel.setBounds(130,70,200,80); //크기 설정
		nickLabel.setFont(nickLabel.getFont().deriveFont(14f));
		settingPanel.add(nickLabel);

		profile = new JLabel("profile");
		profile.setFont(profile.getFont().deriveFont(12f));
		profile.setBounds(130,140,120,120);
		settingPanel.add(profile);
		loadPhoto();
		
		ImageIcon proIcon = new ImageIcon(getClass().getResource("/css/addphoto.png"));
		JButton proButton = new JButton(proIcon);
		proButton.addActionListener(this);
		proButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		proButton.setBorderPainted(false); 
		proButton.setBounds(170,330,40,40);
		settingPanel.add(proButton);
		

		
		//회원가입 버튼 생성 및 위치 생성.
		ImageIcon okIcon = new ImageIcon(getClass().getResource("/css/submit.png"));
		JButton okButton = new JButton(okIcon);
		okButton.setFont(okButton.getFont().deriveFont(14f));
		okButton.setBounds(152,380,80,35);
		okButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		okButton.setBorderPainted(false);
		//버튼 클릭 시 이벤트 리스너.
		okButton.addActionListener(this);
		settingPanel.add(okButton);
		
		// 사진 추가 버튼 이벤트
		proButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser add = new JFileChooser();
		        int result = add.showOpenDialog(UserWindow.this);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectFile = add.getSelectedFile();
		            try {
		                Image image = ImageIO.read(selectFile);
		                imageData = Files.readAllBytes(selectFile.toPath());
		                profile.setIcon(new ImageIcon(image));
		                profile.setText("");
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		

		// 확인 버튼 이벤트
		okButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (imageData == null) {
		            JOptionPane.showMessageDialog(null, "image를 첨부해주세요 ! ", "에러", JOptionPane.ERROR_MESSAGE);
		        } else {
		            try {
		                conn = DbConnect.getConn().getDb();
		                sql = "UPDATE user SET photo=? WHERE id=?";
		                pstmt = conn.prepareStatement(sql);
		                pstmt.setBytes(1, imageData);
		                pstmt.setString(2, thisUser.getId());
		                pstmt.executeUpdate();
		                JOptionPane.showMessageDialog(null, "프로필 이미지가 성공적으로 업데이트되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});

	}
	
	//프로필 사진 불러오기 메서드 
	private void loadPhoto() {
		try {
			conn = DbConnect.getConn().getDb();
            sql = "Select photo from user WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisUser.getId());
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()) {
            	imageData = rs.getBytes("photo");
            	 if (imageData != null && imageData.length > 0) {
                     // 이미지 데이터를 ImageIcon으로 변환
                     ImageIcon imageIcon = new ImageIcon(imageData);
                     // 이미지 크기 조정 (선택적)
                     Image image = imageIcon.getImage().getScaledInstance(profile.getWidth(), profile.getHeight(), Image.SCALE_SMOOTH);
                     ImageIcon resizedIcon = new ImageIcon(image);

                     // JLabel에 이미지 표시
                     profile.setIcon(resizedIcon);
                     profile.setText(""); // 기존 텍스트 제거
                 }
            }
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

	// 폰트설정.

	
	private void setupChatListPanel(JPanel chatListPanel) {

		chatListPanel.setLayout(new BorderLayout());
		
		//채팅방 목록 가져오기
		getRoomList();
		
		chatRoomList = new JList<>(chatListModel);
		
		chatRoomList.setCellRenderer(new ListCellRenderer<ChatRoom>() {
            @Override
            public Component getListCellRendererComponent(
                    JList<? extends ChatRoom> list, ChatRoom value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                
                DefaultListCellRenderer renderer = new DefaultListCellRenderer();
                JLabel label = (JLabel) renderer.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                label.setText(value.getRoomName()); // 채팅방 이름으로 텍스트 설정
                label.setFont(label.getFont().deriveFont(20f)); // 폰트 설정
                
                return label;
            }
            
        });
		
		
		//채팅방 더블클릭시 Window
		

		// 폰트설정.

		chatRoomList.setFixedCellHeight(50);
		
		chatRoomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					ChatRoom selectedRoom = chatRoomList.getSelectedValue();
					if(selectedRoom != null) {
						//서버연결
						Socket socket;
						try {
							socket = new Socket("192.168.0.83",3000);
							ChatRoomWindow chatRoomWindow = new ChatRoomWindow(socket, thisUser, selectedRoom);
							PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
							String initialMessage = selectedRoom.getRoomNum() + "|" + thisUser.getId();
							out.println(initialMessage);
							
							
						} catch (IOException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "채팅방에 접속할 수 없습니다.", "연결 실패", 
		                    		JOptionPane.ERROR_MESSAGE);
		                }

						
					}
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(chatRoomList);
		chatListPanel.add(scrollPane, BorderLayout.CENTER);
		// chatRoomList출력.
		
		
		
//		------------------------------여기부턴 공지사항---------------------------------
		getNotice();
		
		//공지사항 표시할 JLabel 생성.
		JLabel noticeLabel = new JLabel("<html>" + notice.getNotice_post() +'\n' +
										notice.getNotice_date()+ "<html>");
		noticeLabel.setFont(noticeLabel.getFont().deriveFont(16f));
		noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noticeLabel.setBorder(BorderFactory.createTitledBorder("Notice"));
		noticeLabel.setBackground(Color.WHITE);
		
		//공지사항 패널 생성
		JPanel noticePanel = new JPanel(new BorderLayout());
		noticePanel.add(noticeLabel, BorderLayout.CENTER);
		noticePanel.setPreferredSize(new Dimension(400,100));
		noticePanel.setBackground(Color.WHITE);
		
		chatListPanel.add(noticePanel, BorderLayout.SOUTH);
		
		placeButton();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	private void placeButton() {
		//버튼추가.
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 버튼을 3개 배치하기 위한 레이아웃.
		buttonPanel.setBackground(Color.WHITE);
		
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/css/chatlist.png"));
		Image image = originalIcon.getImage(); // ImageIcon에서 Image를 추출
		Image resizedImage = image.getScaledInstance(35, 30, Image.SCALE_SMOOTH); // 이미지 크기 조정
		ImageIcon chatIcon = new ImageIcon(resizedImage); // 조정된 Image로 ImageIcon 재생성

		btnChatList = new JButton(chatIcon);
		btnChatList.setContentAreaFilled(false); //기존버튼디자인 제거 
		btnChatList.setBorderPainted(false);
		btnChatList.setOpaque(false);
		btnChatList.setEnabled(false);

	
		ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/css/addchat.png"));
		Image image2 = originalIcon2.getImage(); // ImageIcon에서 Image를 추출
		Image resizedImage2 = image2.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // 이미지 크기 조정
		ImageIcon addChat = new ImageIcon(resizedImage2); // 조정된 Image로 ImageIcon 재생성
		btnAddChat = new JButton(addChat);
		btnAddChat.setContentAreaFilled(false);
		btnAddChat.setBorderPainted(false);
		btnAddChat.setOpaque(false);

		
		
		ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/css/setting.png"));
		Image image3 = originalIcon3.getImage(); // ImageIcon에서 Image를 추출
		Image resizedImage3 = image3.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // 이미지 크기 조정
		ImageIcon setting = new ImageIcon(resizedImage3); // 조정된 Image로 ImageIcon 재생성
		btnSet = new JButton(setting);
		btnSet.setContentAreaFilled(false);
		btnSet.setBorderPainted(false);
		btnSet.setOpaque(false);
		
		btnChatList.addActionListener(this);
		btnAddChat.addActionListener(this);
		btnSet.addActionListener(this);
		
		buttonPanel.add(btnChatList);
		buttonPanel.add(btnAddChat);
		buttonPanel.add(btnSet);
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 남쪽 하단 배치.

	}
	
	private void getNotice() {
		notice = new Notice();
		conn = DbConnect.getConn().getDb();
		
		sql = "select notice_post, send_date from notice order by send_date desc limit 1";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice.setNotice_post(rs.getString("notice_post"));
				notice.setNotice_date(rs.getString("send_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void getRoomList() {
		conn = DbConnect.getConn().getDb();
		
		sql = "SELECT ROOM_NUM, ROOM_NAME FROM ROOM\r\n"
				+ "WHERE ROOM_NUM IN (SELECT ROOM_NUM FROM ROOM_MEMBER WHERE ID = ?)";
		
		
		try {
			pstmt = conn.prepareStatement(sql);	//pstmt에 db에 sql 삽입
			//pstmt.setString(1, thisUser.getId());
			pstmt.setString(1, thisUser.getId());
			
			ResultSet rs = pstmt.executeQuery();
			

			while(rs.next()) {
				ChatRoom chatRoom = new ChatRoom(rs.getInt("ROOM_NUM"), rs.getString("ROOM_NAME"));
				//MHS

				chatListModel.add(chatListModel.getSize(), chatRoom);
				
			}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}


	public void btnEnabled(boolean chat, boolean add, boolean set) {
		btnChatList.setEnabled(chat);
		btnAddChat.setEnabled(add);
		btnSet.setEnabled(set);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnChatList) {
			cardLayout.show(cardPanel, "ChatList");
			btnEnabled(false, true, true);
		} else if (e.getSource() == btnAddChat) {
			cardLayout.show(cardPanel, "AddChat");
			btnEnabled(true, false, true);
		} else if (e.getSource() == btnSet) {
			cardLayout.show(cardPanel, "Setting");
			btnEnabled(true, true, false);
		}
	}
}
