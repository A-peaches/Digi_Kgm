package digidigi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DbConnect {
	private Connection conn; //Connection 객체. DB 접속해주는 아이 
	private static DbConnect db;
	

	public DbConnect(String url, String user, String pw) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			//conn 객체 미생성시 에러 catch 구문.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("db connetion success");
	}
	
	public static DbConnect getConn() {
		if (db == null) {
            // 여기에 실제 데이터베이스 연결 정보를 넣어주세요.
            db = new DbConnect("jdbc:mysql://118.37.94.80:521/digidigi?serverTimezone=UTC", "root", "pcp1225!@af");
        }
        return db;
	}
	
	 public Connection getDb() {
	        return conn;
	    }
}
