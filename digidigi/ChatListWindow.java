package digidigi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class ChatListWindow extends JFrame implements ActionListener {
	private User thisUser;
	private JList<ChatRoom> chatRoomList;
	DefaultListModel<ChatRoom> chatListModel;
	private JButton btnChatList, btnAddChat, btnSet;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private String sql;
	private String roomName;
	private int roomNum;
	PreparedStatement pstmt;
	Connection conn;
	

	public ChatListWindow(User user) {
		thisUser = user;
		// 채팅방 목록 창.
		setTitle("ChatRoom List");
		setSize(400, 550);
		setLocationRelativeTo(null); // 화면 중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JVM에서 완전한 창닫기. 닫기버튼 클릭시 창 종료.
		chatListModel = new DefaultListModel<>();
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		JPanel chatListPanel = new JPanel();
		JPanel addChatPanel = new JPanel();
		JPanel settingPanel = new JPanel();

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

		// 채팅방 만들기 라벨
		JLabel title = new JLabel(" ♥ New Chatting Room ♥ ");
		title.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		addChatPanel.add(title, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		// '채팅방에 초대하실 아이디를 입력해주세요.' 라벨
		JPanel inputAndInvitePanel = new JPanel(new BorderLayout());
		JLabel inviteLabel = new JLabel("Please enter the ID you want to invite to the chat room.",
				SwingConstants.CENTER);
		inviteLabel.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		inputAndInvitePanel.add(inviteLabel, BorderLayout.NORTH);
		// ID입력 추가 부분

		// 채팅방 이름 입력 필드
		JPanel inputPanel = new JPanel(new FlowLayout());
		JTextField RoomNameField = new JTextField(20);
		RoomNameField.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		inputPanel.add(RoomNameField);

		// ID추가 버튼
		JButton addBtn = new JButton("Add");
		addBtn.setFont(new Font("나눔고딕", Font.PLAIN, 16));
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

		// 생성 버튼
		JButton createBtn = new JButton("Create");
		createBtn.setFont(new Font("나눔고딕", Font.PLAIN, 16));
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

		//방만들기.
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
		// TODO Auto-generated method stub

	}

	// 폰트설정.
	class ChatRoomRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			// 여기에서 폰트 설정을 변경할 수 있습니다.
			setFont(new Font("나눔고딕", Font.PLAIN, 20)); // 원하는 폰트로 변경

			return this;
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
                label.setFont(new Font("나눔고딕", Font.PLAIN, 20)); // 폰트 설정
                
                return label;
            }
        });
		

		// 폰트설정.

		chatRoomList.setFixedCellHeight(50);
		
		JScrollPane scrollPane = new JScrollPane(chatRoomList);
		chatListPanel.add(scrollPane);
		// chatRoomList출력.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 버튼을 3개 배치하기 위한 레이아웃.

		btnChatList = new JButton("목록");
		btnChatList.setEnabled(false);
	
		btnAddChat = new JButton("추가");

		btnSet = new JButton("설정");

		btnChatList.addActionListener(this);
		btnAddChat.addActionListener(this);
		btnSet.addActionListener(this);
		
		buttonPanel.add(btnChatList);
		buttonPanel.add(btnAddChat);
		buttonPanel.add(btnSet);
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 남쪽 하단 배치.

		setVisible(true);

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
