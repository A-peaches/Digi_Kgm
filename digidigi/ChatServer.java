package digidigi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
	private static final int PORT = 3000;
	private List<ClientHandler> clientHandlers = new CopyOnWriteArrayList<>();
	//스레드 안전한 리스트

	public static void main(String[] args) {
		new ChatServer().startServer();
		// 서버구동.
	}

	private void startServer() {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is running in PortNum :"+ PORT);
			
			

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Cilent connected complete :" + clientSocket);
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				clientHandlers.add(clientHandler);
				new Thread(clientHandler).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Client로 부터 받아 메시지전송
	private class ClientHandler implements Runnable {
		private Socket clientSocket;
		private String chatRoomId;
		private String userId;
		private PrintWriter out;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
			try {
				out = new PrintWriter(clientSocket.getOutputStream(),true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void run() {

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
				String message;

				// InputStream으로 클라이언트로부터 받은 메시지가 있을경우
				
				if ((message = reader.readLine()) != null) {
					 handleFirstMessage(message);
	                }
				
				
				 while ((message = reader.readLine()) != null) {
		                System.out.println(clientSocket + "Received2 : " + message);
		                handleIncomingMessage(message);
		            }
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        try {
		            clientSocket.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        clientHandlers.remove(this); // 연결 종료 시 리스트에서 제거
		    }
		}
		private void handleIncomingMessage(String message) {
		    // 메시지에서  채팅방 ID, 사용자 ID, 메시지 본문 분리 (필요한 경우)
		    String[] parts = message.split("\\|");
		    if(parts.length > 0) {
		    	String messageType = parts[0];
		    	if("WHISPER".equals(messageType) && parts.length >= 5) {
		    		String receivedChatRoomId = parts[1];
		    		String senderUserId = parts[2];
		    		String receiverUserId = parts[3];
		    		String whisperMessage = parts[4];
		    		if(isInChatRoom(receivedChatRoomId)) {
		    			for(ClientHandler handler : clientHandlers) {
		    				if(handler.userId.equals(receiverUserId)) {
		    					handler.sendMessage(senderUserId + "(whisepr) > " + whisperMessage);
		    					break;
		    				}
		    			}
		    		}
		    		
		    	}
		    } 
		    
		    if (parts.length >=3) {
		    String receivedChatRoomId = parts[0];
		    String receivedUserId= parts[1];
		    String messageText = parts[2];

//		     현재 핸들러가 해당 채팅방의 메시지를 처리해야 할 경우
		    if (isInChatRoom(receivedChatRoomId)) {
		        for (ClientHandler handler : clientHandlers) {
		            if (handler.isInChatRoom(receivedChatRoomId)) {
		                handler.sendMessage(receivedUserId+" > "+messageText); // 메시지 본문만 전송
		    
		    }
		
		            }
		        }

		    }
		}

		private void handleFirstMessage(String message) {
		    String[] parts = message.split("\\|", 3);
		    if (parts.length >= 2) {
		        this.chatRoomId = parts[0]; // 첫 번째 부분을 사용자 ID로 설정
		        this.userId = parts[1]; // 두 번째 부분을 채팅방 ID로 설정
		        // 첫 메시지 처리시, 메시지 본문(세 번째 부분)은 사용하지 않거나, 필요에 따라 처리
		    }
		}
		private void sendMessage(String message) {
		     out.println(message);
		}
		
		private boolean isInChatRoom(String roomId) {
			return chatRoomId.equals(roomId);
		}

	}
}
