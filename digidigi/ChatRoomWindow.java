package digidigi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class ChatRoomWindow extends JFrame {

	private User thisUser;
	private ChatRoom chatRoom;
	private Socket socket;
	private JTextArea chatArea;
	private JTextField messageField;
	private JButton sendButton;
	private JButton searchButton;
	private String sql;
	private String messageToSend;
	private int manager;
	private PrintWriter out;
	private boolean isNull;
	PreparedStatement pstmt;
	Connection conn;

	public ChatRoomWindow(User user, ChatRoom chatRoom) {
		this(null, user, chatRoom);
		// UI확인용 생성자
	}

	public ChatRoomWindow(Socket socket, User user, ChatRoom chatRoom) {

		conn = DbConnect.getConn().getDb();
		chatArea = new JTextArea();
		chatArea.setEditable(false);// 편집불가
		// 메시지 받을 부분 생성

		new MessageListener(socket, chatArea).start();
		this.socket = socket;
		this.thisUser = user;
		this.chatRoom = chatRoom;
		loadChat();
		roomUI();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void roomUI() {
		setTitle(chatRoom.getRoomName() + " - " + thisUser.getId()); // 채팅방 이름과 현재 접속 id
		setSize(400, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 종료옵션

		// 채팅방 종료시 socket close.
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (socket != null && !socket.isClosed()) {
						socket.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				dispose();
			}

		});
		placeRoom();
	}

	//귓속말은 프라이버시. admin조회불가.
	private void loadChat() {
		chatArea.setText("");

		//select id,chat from room_chat where room_num = 18 and DATE(send_date) = '2024-04-04'
		//		and (whisper_id is Null OR whisper_id = 'popo'); 
		
		sql = "select id,chat from room_chat where room_num = ? and DATE(send_date) = CURDATE() "
				+ "and (whisper_id is Null OR whisper_id = ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom.getRoomNum());
			pstmt.setString(2,thisUser.getId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String id = rs.getString("id");
				String chat = rs.getString("chat");

				chatArea.append(id + " > " + chat + "\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String status = "< ";
		sql = "select id from room_member where room_num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom.getRoomNum());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				status += rs.getString("id") + " ";
			}
			
			chatArea.append("[   notice : 현재 채팅방의 참여자는 " +'\n'+ status +" > 입니다.    ]" +'\n');
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadChatHistoryForDate(String inputDate) {
		chatArea.setText("");

		sql = "select id,chat from room_chat where room_num = ? and DATE(send_date) = ? "
				+ "and (whisper_id is Null OR whisper_id = ?)";  // '2024-04-03 형태가능''
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom.getRoomNum());
			pstmt.setString(2, inputDate);
			pstmt.setString(3,thisUser.getId());

			ResultSet rs = pstmt.executeQuery();
			boolean hasData = false;
			while (rs.next()) {

				hasData = true;
				String id = rs.getString("id");
				String chat = rs.getString("chat");

				chatArea.append(id + " > " + chat + "\n");
			}

			if (!hasData) {
				// 조회된 데이터가 없을 경우 메시지 표시
				chatArea.append(" 대화내용이 없습니다!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void placeRoom() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/css/talk.png"));
	    // 프레임의 아이콘으로 설정
	    setIconImage(icon.getImage());
	    
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null); // 패널의 레이아웃 매니저를 null로 설정
		
		JLabel roomNameLabel=new JLabel("  ♥   "+ chatRoom.getRoomName() +" - "+thisUser.getId());
		roomNameLabel.setFont(roomNameLabel.getFont().deriveFont(20f));
		roomNameLabel.setBackground(Color.WHITE);
		roomNameLabel.setBounds(0,10,300,30);
		add(roomNameLabel);
		
		ImageIcon searchIcon = new ImageIcon(getClass().getResource("/css/search.png"));
		searchButton = new JButton("검색",searchIcon);
		searchButton.setBounds(309,10,58,30);
		searchButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		searchButton.setBorderPainted(false);
		add(searchButton);
		
		searchButton.addActionListener(e -> {
			if (searchButton.getText().equals("검색")) {
				//검색상태일떄의 동작.
				String inputDate = JOptionPane.showInputDialog("날짜를 입력하세요. (예: 2024-04-01)");
				if (inputDate != null && !inputDate.isEmpty()) {

					ImageIcon restoreIcon = new ImageIcon(getClass().getResource("/css/restore.png"));
					loadChatHistoryForDate(inputDate); // 특정 날짜의 채팅 내용 로드
					searchButton.setText("복구"); // 버튼 라벨 변경
					searchButton.setIcon(restoreIcon); //
					searchButton.setBounds(320,5,50,30);
				}
			} else if (searchButton.getText().equals("복구")) {
		        loadChat(); // 오늘 날짜의 채팅 내용 로드
		        searchButton.setText("검색"); // 버튼 라벨을 원래대로 변경
		        searchButton.setIcon(searchIcon); // 
		        searchButton.setBounds(309,10,58,30);
		        
		    }
		});
		ImageIcon helpIcon = new ImageIcon(getClass().getResource("/css/help.png"));
		JButton helpButton = new JButton(helpIcon);
		helpButton.setBounds(269, 10, 30, 30);
		helpButton.setContentAreaFilled(false); // 기존 버튼 디자인 제거
		helpButton.setBorderPainted(false); // 테두리 제거
		add(helpButton);
		
		helpButton.addActionListener(e -> {
				JOptionPane.showMessageDialog(this,
				        "1. 강퇴: /강퇴 + 사용자ID\n2. 초대: /초대 + 사용자ID\n"
				        + "3. 귓속말: /w + 사용자ID + 대화내용\n* 강퇴와 초대는 방장만 가능합니다.",
				        "digidigi Talk 도움말", // 대화상자 제목
				        JOptionPane.INFORMATION_MESSAGE);			
		});
		
		//채팅 영역 생성
		chatArea.setFont(chatArea.getFont().deriveFont(15f));
		JScrollPane scrollPane = new JScrollPane(chatArea);
		scrollPane.setBounds(10, 40, 360, 400);
		
		
		add(scrollPane);
		

		messageField = new JTextField();
		messageField.setBounds(10, 450, 300, 50);
		messageField.setFont(messageField.getFont().deriveFont(15f));
		panel.add(messageField);
		

		
		ImageIcon sendIcon = new ImageIcon(getClass().getResource("/css/send2.png"));
		sendButton = new JButton(sendIcon);
		sendButton.setContentAreaFilled(false); //기존버튼디자인 제거 
		sendButton.setBorderPainted(false);
		//버튼 클릭 시 이벤트 리스너.
		sendButton.setBounds(300, 443, 80, 60); // 전송 버튼의 위치와 크기 설정
		panel.add(sendButton);
		    
		add(panel);
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMsg();
			}
		});
		
		messageField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	sendMsg(); // 별도로 분리한 메시지 전송 메서드 호출
		    }
		});
		
	}
	

	private void sendMsg() {
		String message = messageField.getText().trim();
		
		//메시지 읽어들어옴
		
		if(!message.isEmpty()) {
			try {
				sql = "select manager from room_member where id = ? and room_num = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,thisUser.getId());
					pstmt.setInt(2,chatRoom.getRoomNum());
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						manager = rs.getInt("manager");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				message = kickInvite(message);
				
				if(message.isEmpty()) {
					return;
				}

				if (message.startsWith("/w")) {
					int firstSpaceIndex = message.indexOf(" ", 3); // 첫번째공백기준으로 나오는 귓속말대상자
					if (firstSpaceIndex != -1) {
						String whisperUserId = message.substring(3, firstSpaceIndex); // 귓속말대상자
						String whisperMessage = message.substring(firstSpaceIndex + 1); // 귓속말 메시지

						messageToSend = "WHISPER|" + chatRoom.getRoomNum() + "|" + thisUser.getId() + "|"
								+ whisperUserId + "|" + whisperMessage;
						
						saveWhisperMsg(whisperUserId, whisperMessage);
					}
				} else {
					saveMsg(message);
					// 소켓의 출력 스트림을 통해 서버에 메시지 전송
					messageToSend = chatRoom.getRoomNum() + "|" + thisUser.getId() + "|" + message;

				}
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(messageToSend);

				// 메시지필드 초기화
				messageField.setText("");
			}

		 catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		}
	}
	
	private String kickInvite(String message) {
		if (message.startsWith("/강퇴 ")) {
			if (manager == 1) {
				String[] parts = message.split(" ", 2);
				String kickedUser = parts.length > 1 ? parts[1] : "";

				
				try {
				sql = "select count(*) from room_member where id =? AND room_num =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, kickedUser);
				pstmt.setInt(2, chatRoom.getRoomNum());
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next() && rs.getInt(1)>0) {
					
					message = kickedUser + "님이 강퇴되었습니다.";
			
					sql = "delete from room_member where id = ? and room_num = ?";
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, kickedUser);
					pstmt.setInt(2, chatRoom.getRoomNum());
					pstmt.executeUpdate();
				}else {
					 JOptionPane.showMessageDialog(null, kickedUser + " 사용자는 존재하지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);
			            return ""; // 처리 중단
				}
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "강퇴 기능은 방장만 사용할 수 있습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				
			}
		} else if (message.startsWith("/초대 ")) {
			if (manager == 1) {
				String[] parts = message.split(" ", 2);
				String invitedUser = parts.length > 1 ? parts[1] : "";
				try {
				sql = "SELECT COUNT(*) FROM user WHERE id = ?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, invitedUser);
		        ResultSet rs = pstmt.executeQuery();
		        
		        if (rs.next() && rs.getInt(1) >0){
		        	sql = "insert into room_member (room_num,id) values (?,?)";
		        	
					message = invitedUser + "님이 초대되었습니다.";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, chatRoom.getRoomNum());
					pstmt.setString(2, invitedUser);
					pstmt.executeUpdate();
		        }else {
		        	JOptionPane.showMessageDialog(null, invitedUser + " 사용자는 존재하지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);
		            return ""; // 처리 중단
		        }
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "초대 기능은 방장만 사용할 수 있습니다.", "에러", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	
			
		return message;
	}

	// 메시지 db밀어넣기
	private void saveMsg(String message) {
		sql = "insert into room_chat \r\n" // "(14, 'pipi', '삐!!!!!!!');"
				+ "(room_num, id, chat) values \r\n" + "(?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom.getRoomNum());
			pstmt.setString(2, thisUser.getId());
			pstmt.setString(3, message);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void saveWhisperMsg(String whisperUserId, String message) {
		sql = "insert into room_chat \r\n" // "(14, 'pipi', '삐!!!!!!!');"
				+ "(room_num, id, chat,whisper_id) values \r\n" + "(?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatRoom.getRoomNum());
			pstmt.setString(2, thisUser.getId());
			pstmt.setString(3,"(whisper)"+message);
			pstmt.setString(4, whisperUserId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// 메시지 리스너 쓰레드
	private class MessageListener extends Thread {
		private Socket socket;
		private JTextArea chatArea;

		public MessageListener(Socket socket, JTextArea chatArea) {
			this.socket = socket;
			this.chatArea = chatArea;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String message;

				while ((message = in.readLine()) != null) {
					String finalMessage = message;
					SwingUtilities.invokeLater(() -> chatArea.append(finalMessage + "\n"));
				}
			} catch (SocketException e) {
				System.out.println("채팅방을 나갔습니다.");
				// 여기에 필요한 종료 로직 추가 (예: 리소스 정리)
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
