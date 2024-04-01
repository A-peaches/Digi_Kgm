package digidigi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton btnChatList, btnAddChat, btnSet;
	private CardLayout cardLayout;
	private JPanel cardPanel;

	public ChatListWindow(User user) {
		thisUser = user;
		// 채팅방 목록 창.
		setTitle("ChatRoom List");
		setSize(400, 550);
		setLocationRelativeTo(null); // 화면 중앙에 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// JVM에서 완전한 창닫기. 닫기버튼 클릭시 창 종료.

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
		JLabel title = new JLabel(" ♥ New Chatting Room ♥");
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
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("나눔고딕", Font.PLAIN, 16));
		btnPanel.add(cancelBtn);

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

		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// thisUser를 방장으로 room을 생성하면서, id만큼 멤버등록.
				String roomName = thisUser.getId()+", ";
				for (int i = 0; i < listModel.size(); i++) {
					roomName += listModel.get(i)+", ";
				}
				ChatRoom ch = new ChatRoom(roomName);
				System.out.println(ch.getRoomName());
				 
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "ChatList");
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
		DefaultListModel<ChatRoom> chatListModel = new DefaultListModel<>();
		

		chatRoomList = new JList<>(chatListModel);
		chatRoomList.setCellRenderer(new ChatRoomRenderer());
		// 폰트설정.

		chatRoomList.setFixedCellHeight(50);
		JScrollPane scrollPane = new JScrollPane(chatRoomList);
		chatListPanel.add(scrollPane);
		// chatRoomList출력.

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 버튼을 3개 배치하기 위한 레이아웃.

		btnChatList = new JButton("목록");
		btnChatList.setEnabled(false);
		btnChatList.addActionListener(this);
		btnAddChat = new JButton("추가");
		btnAddChat.addActionListener(this);
		btnSet = new JButton("설정");
		btnSet.addActionListener(this);

		buttonPanel.add(btnChatList);
		buttonPanel.add(btnAddChat);
		buttonPanel.add(btnSet);
		add(buttonPanel, BorderLayout.SOUTH); // 버튼 남쪽 하단 배치.

		setVisible(true);

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
