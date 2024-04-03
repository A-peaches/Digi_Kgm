package digidigi;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(3000);
			System.out.println("Server is running in PortNum : 3000");

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Cilent connected complete :" + clientSocket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
