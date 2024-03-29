package Day20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//Database 접속 
public class DbExam01 {
	Connection conn; //Connection 객체. DB 접속해주는 아이
	PreparedStatement pstmt; // 쿼리문을 담는 객체
	Scanner sc;
	//디폴트 생성자. this호출
	public DbExam01() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC","root","qwe123!@#");
	}

	public DbExam01(String url, String user, String pw) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			//conn 객체 미생성시 에러 catch 구문.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void dbInput() {
		try {
			String sql = "insert into student2 values(7,'superman')";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void dbInput2() {
		try {
			String sql = "insert into student2 values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  8);
			//첫번쨰 물음표에 값 쏙
			pstmt.setString(2, "superman");
			//두번째 물음표에 값 쏙
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void dbInput3(String[] str) {
		try {
			String sql = "insert into student2 values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			for(int i =0; i<str.length; i++) {
				pstmt.setInt(1, i);
				pstmt.setString(2, str[i]);
				pstmt.executeUpdate();
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void dbInput4() {
		sc = new Scanner(System.in);
		System.out.println("id 값을 입력해주세요.");
		int id = sc.nextInt();
		System.out.println("이름을 입력해주세요.");
		String name = sc.next(); 
		try {
			String sql = "insert into student2 values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	private void createDb() {
		try {
			String s = "drop table if exists student2;"; 
			//첫번째 쿼리문
			String sql = "create table student2(\r\n"
					+ "id int,\r\n"
					+ "username varchar(20),\r\n"
					+ "primary key(id) \r\n" +")\r\n";
			//두번째쿼리문
			pstmt = conn.prepareStatement(s);
			//쿼리문 객체에 숑담기
			pstmt.executeUpdate();
			//쿼리문 실행
			
			pstmt = conn.prepareStatement(sql);
			//쿼리문 객체에 또 숑담기
			pstmt.executeUpdate();
			//쿼리문 실행!!
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	private void result() throws SQLException {
		String sql = "select * from student2";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			
			System.out.println(id+" "+name);
		}
		
	}
	
	private void update(String name) {
		try {
			String sql = "update student2 set username=? where id = 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void delete(String str) {
		try {
			String sql = "delete from student2 where id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		System.out.println(str +" 삭제가 완료되었습니다.");
	}
	
	public static void main(String[] args) throws SQLException {
		DbExam01 exam = new DbExam01();

		System.out.println("success!");
		
		String []str = {"AAA","BBB","CCC"};
//		exam.dbInput();
//		exam.dbInput2();
//		exam.dbInput3(str);
//		exam.dbInput4();
//		exam.result();
//		exam.update("DDD");
		exam.delete("DDD");
		exam.result();
	}


}
