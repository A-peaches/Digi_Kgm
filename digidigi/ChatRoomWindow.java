package digidigi;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

public class ChatRoomWindow extends JFrame{
	
	private User user;
	private ChatRoom chatRoom;
	private Socket socket;
	private JTextArea chatArea;
	private JTextField messageField;
	
	public ChatRoomWindow() {
		roomUI(); //UI 확인용 생성자
	}
	
	public ChatRoomWindow(Socket socket, User user, ChatRoom chatRoom) {
		this.socket = socket;
		this.user = user;
		this.chatRoom = chatRoom;
		roomUI();
	}


	private void roomUI() {
		setTitle(chatRoom.getRoomName() + " - " + user.getId()); //채팅방 이름과 현재 접속 id 
		setSize(400,550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //종료옵션
		
		JPanel panel = new JPanel();
		
		chatArea = new JTextArea(30,30); //채팅 영역 생성
		chatArea.setEditable(false);// 편집불가능..?
		
		messageField = new JTextField(30);
		
		panel.add(chatArea);
		panel.add(messageField);
		add(panel);
		
	}
	
}
