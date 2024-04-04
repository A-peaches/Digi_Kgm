package digidigi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
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
import javax.swing.SwingUtilities;

public class AdminWindow extends JFrame implements ActionListener {
	private User thisUser;
	private JList<User> memberList;
	DefaultListModel<User> memberListModel;
	private JList<ChatRoom> chatRoomList;
	DefaultListModel<ChatRoom> chatListModel;
	private JButton btnMemberList, btnChatList, btnSet;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private String sql;
	private String roomName;
	private int roomNum;
	private JLabel profile;
	private byte[] imageData;
	private JTextField noticeField;
	private JButton btnSendNotice;
	Notice notice;
	PreparedStatement pstmt;
	Connection conn;                 

	public AdminWindow(User user) {
		thisUser = user;
		
		
		// 채팅방 목록 창.
		setTitle("digidigi Talk" + " - " + thisUser.getId());
		setSize(400, 550);
		setLocationRelativeTo(null); // 화면 중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JVM에서 완전한 창닫기. 닫기버튼 클릭시 창 종료.
		chatListModel = new DefaultListModel<>();
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(Color.WHITE);
		
		JPanel memberListPanel = new JPanel();
		JPanel chatListPanel = new JPanel();
		JPanel settingPanel = new JPanel();

		// 채팅방추가패널
	    ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    setIconImage(icon.getImage());
	    
	    
		setupMemberListPanel(memberListPanel);
		setupChatListPanel(chatListPanel);
		setupSettingPanel(settingPanel);

		cardPanel.add(memberListPanel, "memberList");
		cardPanel.add(chatListPanel, "chatList");
		cardPanel.add(settingPanel, "Setting");

		add(cardPanel, BorderLayout.CENTER);

	}

	
	private void setupMemberListPanel(JPanel memberListPanel) {

		// 패널레이아웃설정
		memberListPanel.setLayout(new BorderLayout());		
		//채팅방 목록 가져오기
		getMemberList();
		
		memberList = new JList<>(memberListModel);
		
		//List component형태로 불러오기.
	    memberList.setCellRenderer(new ListCellRenderer<User>() {
	        @Override
	        public Component getListCellRendererComponent(JList<? extends User> list, User user, int index,
	                                                      boolean isSelected, boolean cellHasFocus) {
	            JLabel label = new JLabel();
	            label.setOpaque(true);
	            // ID와 닉네임 설정
	            label.setText(user.getId() + " (" + user.getNickName() + ")");
	            // 프로필 사진 설정 (사용자가 사진을 가지고 있는 경우)
	            if (user.getPhoto() != null && user.getPhoto().length > 0) {
	                ImageIcon imageIcon = new ImageIcon(user.getPhoto());
	                // 이미지 크기 조정 (선택적)
	                Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	                label.setIcon(new ImageIcon(image));
	                label.setFont(label.getFont().deriveFont(17f)); // 폰트 설정
	            }

	            // 선택되거나 포커스를 받은 항목의 배경 색상 처리
	            if (isSelected) {
	                label.setBackground(list.getSelectionBackground());
	                label.setForeground(list.getSelectionForeground());
	            } else {
	                label.setBackground(list.getBackground());
	                label.setForeground(list.getForeground());
	            }

	            return label;
	        }
	    });

		memberList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {//더블  클릭이벤트!
					int index = memberList.locationToIndex(e.getPoint()); //클릭된 JList의 idx추출.
					User selectedUser = memberListModel.getElementAt(index);
					//선택된 회원 객체 가져오기
					
					//상세 정보 표시 화면 열기
					userInfoWindow(selectedUser); // 선택된 유저 객체 정보 넘겨주기
				}
			}

		});

		
		memberList.setFixedCellHeight(50);
		
		JScrollPane scrollPane = new JScrollPane(memberList);
		memberListPanel.add(scrollPane);
		// chatRoomList출력.

	}
	
	//회원클릭 시 디테일 창 
	public void userInfoWindow(User user) {
		
		JDialog infoDialog = new JDialog();
		infoDialog.setTitle("Member Infomation");
	    ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    infoDialog.setIconImage(icon.getImage());
		infoDialog.setSize(300,200);
		infoDialog.setLayout(new BorderLayout());
		infoDialog.getContentPane().setBackground(Color.WHITE);

		
		// 프로필 사진 표시
		JLabel photoLabel;
		if (user.getPhoto() == null) {
		    // photo가 없으면 "no profile" 라벨 생성
		    photoLabel = new JLabel("No Profile", JLabel.CENTER);
		} else {
		    // photo가 있으면, 사진을 라벨에 설정
		    ImageIcon imageIcon = new ImageIcon(user.getPhoto());
		    photoLabel = new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
		infoDialog.add(photoLabel, BorderLayout.NORTH);
		
		//ID표시
		JLabel idLabel = new JLabel("User ID : " + user.getId());
		infoDialog.add(idLabel, BorderLayout.CENTER);
		
		JCheckBox cutOffCk = new JCheckBox("Login Limit ", user.isCutOff());
		infoDialog.add(cutOffCk, BorderLayout.SOUTH);
		cutOffCk.setBackground(Color.WHITE);
		
		//detail 창을 닫을 경우 최종으로 DB에 반영.
		infoDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				boolean isCk = cutOffCk.isSelected();
				user.setCutOff(isCk);
				conn = DbConnect.getConn().getDb();
				sql = "UPDATE user SET cut_off =? WHERE id=?";
	
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setBoolean(1, user.isCutOff());
					pstmt.setString(2, user.getId());
					pstmt.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		setLocationRelativeTo(null);
		infoDialog.setVisible(true);
		
	}

	//회원목록 가져오기
	private void getMemberList() {
		conn = DbConnect.getConn().getDb();
		memberListModel = new DefaultListModel<>();
		
		sql = "SELECT id,pw,nickname,photo,cut_off from user";
		
		
		try {
			pstmt = conn.prepareStatement(sql);	//pstmt에 db에 sql 삽입

			ResultSet rs = pstmt.executeQuery();
			

			while(rs.next()) {
				User user = new User(rs.getString("id"), rs.getString("pw"), rs.getString("nickname"),
						rs.getBytes("photo"), false, rs.getBoolean("cut_off"));
				

				memberListModel.add(chatListModel.getSize(), user);
				
			}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//설정 창
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
		        int result = add.showOpenDialog(AdminWindow.this);
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
	
	//회원사진 가져오기
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
                label.setFont(label.getFont().deriveFont
                		(20f)); // 폰트 설정
                
                return label;
            }
        });
		
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
		chatListPanel.add(scrollPane);
		// chatRoomList출력.
		
		
//		------------------------------여기부턴 공지사항---------------------------------
		//공지사항 입력 필드와 버튼 추가
		JPanel noticeInputPanel = new JPanel();
		noticeInputPanel.setLayout(new BorderLayout());
		noticeInputPanel.setBackground(Color.WHITE);
		
		placeNotice();
		getNotice(); 
		
		noticeInputPanel.add(noticeField, BorderLayout.CENTER);
		noticeInputPanel.add(btnSendNotice, BorderLayout.EAST);
		
		
		
		chatListPanel.add(noticeInputPanel, BorderLayout.SOUTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 버튼을 3개 배치하기 위한 레이아웃.
		buttonPanel.setBackground(Color.WHITE);
		
		//버튼추가
		placeButton();
		
		btnChatList.addActionListener(this);
		btnMemberList.addActionListener(this);
		btnSet.addActionListener(this);
		
		buttonPanel.add(btnMemberList);
		buttonPanel.add(btnChatList);
		buttonPanel.add(btnSet);
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 남쪽 하단 배치.
		
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	private void placeNotice() {
		noticeField = new JTextField(); // 공지사항 입력 필드.
		ImageIcon sendIcon = new ImageIcon(getClass().getResource("/css/send.png"));
		btnSendNotice = new JButton(sendIcon);
		btnSendNotice.setContentAreaFilled(false); //기존버튼디자인 제거 
		btnSendNotice.setBorderPainted(false);
		btnSendNotice.addActionListener(e -> sendNotice());
	}
	
	private void placeButton() {

		ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/css/member.png"));
		Image image2 = originalIcon2.getImage(); // ImageIcon에서 Image를 추출
		Image resizedImage2 = image2.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // 이미지 크기 조정
		ImageIcon memberIcon = new ImageIcon(resizedImage2); // 조정된 Image로 ImageIcon 재생성
		btnMemberList = new JButton(memberIcon);
		btnMemberList.setEnabled(false);
		btnMemberList.setContentAreaFilled(false);
		btnMemberList.setBorderPainted(false);
		btnMemberList.setOpaque(false);
		
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/css/chatlist.png"));
		Image image = originalIcon.getImage(); // ImageIcon에서 Image를 추출
		Image resizedImage = image.getScaledInstance(35, 30, Image.SCALE_SMOOTH); // 이미지 크기 조정
		ImageIcon chatIcon = new ImageIcon(resizedImage); // 조정된 Image로 ImageIcon 재생성
		btnChatList = new JButton(chatIcon);
		btnChatList.setContentAreaFilled(false);
		btnChatList.setBorderPainted(false);
		btnChatList.setOpaque(false);
		
		ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/css/setting.png"));
		Image image3 = originalIcon3.getImage(); // ImageIcon에서 Image를 추출
		Image resizedImage3 = image3.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // 이미지 크기 조정
		ImageIcon setting = new ImageIcon(resizedImage3); // 조정된 Image로 ImageIcon 재생성
		btnSet = new JButton(setting);
		btnSet.setContentAreaFilled(false);
		btnSet.setBorderPainted(false);
		btnSet.setOpaque(false);
	}
	
	private void sendNotice() { //공지사항 보내기.
		String notice = noticeField.getText();
		if(!notice.isEmpty()) {
			conn = DbConnect.getConn().getDb();
			
			sql = "INSERT INTO notice (notice_post) VALUES (?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, notice);
				pstmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "공지사항이 전송되었습니다.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "공지사항 전송에 실패했습니다.");
			}
			
		}
	}

	//초기 공지사항 로드
	
	public void getNotice() {
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
		
		noticeField.setText(notice.getNotice_post() + notice.getNotice_date());
		
	}
	public void getRoomList() {
		conn = DbConnect.getConn().getDb();
		
		sql = "SELECT ROOM_NUM, ROOM_NAME FROM ROOM";
		
		
		try {
			pstmt = conn.prepareStatement(sql);	//pstmt에 db에 sql 삽입

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
	


	public void btnEnabled(boolean member, boolean chat, boolean set) {
		btnMemberList.setEnabled(member);
		btnChatList.setEnabled(chat);
		btnSet.setEnabled(set);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnMemberList) {
			cardLayout.show(cardPanel, "memberList");
			btnEnabled(false, true, true);
		} else if (e.getSource() == btnChatList) {
			cardLayout.show(cardPanel, "chatList");
			btnEnabled(true, false, true);
		} else if (e.getSource() == btnSet) {
			cardLayout.show(cardPanel, "Setting");
			btnEnabled(true, true, false);
		}
	}
}
