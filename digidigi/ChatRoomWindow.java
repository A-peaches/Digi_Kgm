package digidigi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

public class ChatRoomWindow extends JFrame{
	
	private User user;
	private ChatRoom chatRoom;
	private Socket socket;
	private JTextArea chatArea;
	private JTextField messageField;
	private JButton sendButton;
	
	public ChatRoomWindow( User user, ChatRoom chatRoom) {
		this(null,user,chatRoom);
		//UI확인용 생성자
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
		
	    placeRoom();
	}
	
	private void placeRoom() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    setIconImage(icon.getImage());
	    
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null); // 패널의 레이아웃 매니저를 null로 설정
		
		JLabel roomNameLabel=new JLabel("  ♥   "+ chatRoom.getRoomName() +" - "+user.getId());
		roomNameLabel.setFont(roomNameLabel.getFont().deriveFont(20f));
		roomNameLabel.setBackground(Color.WHITE);
		roomNameLabel.setBounds(0,10,300,30);
		add(roomNameLabel);
		
		chatArea = new JTextArea(); //채팅 영역 생성
		chatArea.setEditable(false);// 편집불가능..?
		JScrollPane scrollPane = new JScrollPane(chatArea);
		scrollPane.setBounds(10, 40, 360, 400);
		add(scrollPane);
		

		messageField = new JTextField();
		messageField.setBounds(10, 450, 300, 50);
		panel.add(messageField);
		

		
		ImageIcon sendIcon = new ImageIcon(getClass().getResource("/css/send2.png"));
		sendButton = new JButton(sendIcon);
		sendButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		sendButton.setBorderPainted(false);
		//버튼 클릭 시 이벤트 리스너.
		sendButton.setBounds(300, 443, 80, 60); // 전송 버튼의 위치와 크기 설정
		panel.add(sendButton);
		    
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = messageField.getText().trim();
				//메시지 읽어들어옴
				
				try {
					
					//소켓의 출력 스트림을 통해 서버에 메시지 전송
					PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
					out.println(message);
					
					//메시지필드 초기화
					messageField.setText("");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		add(panel);
	}
}
