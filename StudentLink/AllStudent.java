package StudentLink;

import java.util.*;
import java.sql.*;
import Day14.LinkNode;



public class AllStudent implements ProgramFunction{
	Scanner sc = new Scanner(System.in);
	Student newSt;
	Student head;
	Student cur;
	Student del;
	Student prev;
	Connection conn;
	PreparedStatement pstmt;
	// Constructor
	// 모든 학생을 관리할 수 있는 객체 생성시, 모든학생들을 담을 stuArray를 생성한다.
	// 매개변수 생성자는 허용되지않는다.
	public AllStudent() throws SQLException {
		//DB연동
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC",
				"root","qwe123!@#");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "select * from stu";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			//1은 auto auto_increment임.
			String name = rs.getString(2);
			int kor = rs.getInt(3);
			int eng = rs.getInt(4);
			int mat = rs.getInt(5);
			
			roadData(name,kor,eng,mat);
		}
	}

	


	public void mainDisplay() {
		System.out.println("=============== 학생 성적 관리 프로그램 ===============" 
				+ '\n' + "1. 학생 성적 입력" + '\n' + "2. 학생 성적 수정"
				+ '\n' + "3. 학생 성적 검색" 
				+ '\n' + "4. 학생 성적 출력" 
				+ '\n' + "5. 학생 성적 삭제" + '\n' + "6. 프로그램 종료 " + '\n'
				+ "=================================================");
	}
	public void roadData(String name, int kor, int eng, int mat) {
		if(head == null) {
			newSt = new Student();
			newSt.setName(name);
			newSt.setKor(kor);
			newSt.setEng(eng);
			newSt.setMat(mat);
			head = cur = newSt; 
			newSt.next = null;
		
		} else {
			newSt = new Student();
			newSt.setName(name);
			newSt.setKor(kor);
			newSt.setEng(eng);
			newSt.setMat(mat);
			insertAdd();
		}
	}
	@Override
	public void addData() {
		if(head == null) {
			System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
			newSt = new Student();
			newSt.setName(sc.next());
			newSt.setKor(sc.nextInt());
			newSt.setEng(sc.nextInt());
			newSt.setMat(sc.nextInt());
			head = cur = newSt; 
			newSt.next = null;
		
		} else {
			System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
			newSt = new Student();
			newSt.setName(sc.next());
			newSt.setKor(sc.nextInt());
			newSt.setEng(sc.nextInt());
			newSt.setMat(sc.nextInt());
			insertAdd();
		}
	}
	
	public void insertAdd() {
		//만약에 , newSt보다 head의 평균이 같거나 작으면,
		if(head == null || newSt.getAvg() > head.getAvg()) {
			newSt.next = head;
			head = newSt;
		//만약에 , newSt보다 head의 평균이 같거나 작지 않다면,
		} else {
			cur = head;
			while(cur.next != null && newSt.getAvg() < cur.next.getAvg()) {
				cur = cur.next;
			} //이러면 삽입할 전 노드를 알게됨.
			newSt.next = cur.next;
			cur.next = newSt;
		}
		//
	}
	public void nullPrev() {
		//null이전값 탐색. 만약 첫번쨰의 next가 null이면, 두번째에 넣어야함.
		cur = head;
		while (cur.next != null) {
			cur = cur.next;
		}
	}


	@Override
	public void allView() {
		System.out.println("================ 모든 학생들의 성적표 =================" + 
						'\n' + "이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
		cur = head;
		while (cur != null) {
			cur.print();
			cur = cur.next;
		}
	}


	public void avgCur(int avg) {
		cur = head;
		while (cur != null) { 
			if(cur.getAvg() >= avg) {
				cur.print();
			}
			cur = cur.next;
		}
	}

	public void nameCur(String name) { //검색한 학생의 인덱스를 반환해주는 메서드
		cur = head;
		while (cur != null) { 
			if(cur.getName().equals(name)) {
				break;
			}else {
				prev = cur;
				cur = cur.next; 
			}
			//이름이다르면, 다음 노드로 넘어간다.
		}
	}
	
	public void deleteCur(String name) {

	    // 헤드 노드가 삭제 대상인 경우를 먼저 처리
	    if (head.getName().equals(name)) {
	        head = head.next; // 헤드를 다음 노드로 업데이트
	        return;
	    }

	    cur = head;
	    while (cur.next != null) {
	        if (cur.next.getName().equals(name)) {
	            del = cur.next;
	        	cur.next = cur.next.next; // cur의 다음 노드를 삭제 대상 노드의 다음 노드로 변경
	        	del.next = null;
	            return; // 삭제 완료
	        }
	        cur = cur.next; // 다음 노드로 이동
	    }
	}

	
	public void updateInsert() {
	    if(prev != null) {
	        prev.next = cur.next;
	    } else {
	        head = cur.next;
	    }
	    cur.next = null; // cur의 next를 분리
	    newSt = cur; // newSt에 cur 할당
	    insertAdd(); // 삽입 로직으로 이동
	}

	
	@Override
	public void deleteData() {
		System.out.println("삭제할 학생의 이름을 입력해주세요 : ");
		String name = sc.next();
		deleteCur(name);
	}
	



	@Override
	public void updateData() {
		int updateNum = 0;
		int prev = 0;
		int close = 0;

		do {
			System.out.println("수정할 학생의 이름을 입력해주세요 : ");
			nameCur(sc.next()); //수정할 학생을 cur로 잡는다.

			if (cur != null) {

				cur.print();
				updateNum = 0;
				
				while (updateNum != 4) {
					System.out.println("수정할 과목을 입력해주세요." + '\n' + "1. 국어" + '\t' + "2. 영어 " + '\n' + "3. 수학" + '\t'
							+ "4. 다른 학생 수정" + '\n');
					updateNum = sc.nextInt();
					switch (updateNum) {
					case 1:
						System.out.println("수정할 점수를 입력해주세요.");
						prev = cur.getKor();
						cur.setKor(sc.nextInt());
						System.out.println(
								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + cur.getKor());
						updateInsert();
						return;
					case 2:
						System.out.println("수정할 점수를 입력해주세요.");
						prev = cur.getEng();
						cur.setEng(sc.nextInt());
						System.out.println(
								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + cur.getEng());
						updateInsert();
						return;
					case 3:
						System.out.println("수정할 점수를 입력해주세요.");
						prev = cur.getMat();
						cur.setMat(sc.nextInt());
						System.out.println(
								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + cur.getMat());
						updateInsert();
						return;
					case 4:
						break; // 다른학생선택시 switch문을 벗어난다.
					default:
						System.out.println("1 ~ 4 사이의 숫자로 입력해주세요.");
					}

					if (updateNum == 4) {
						break; // switch문을 벗어난 뒤 for문을 벗어난다.
					}
				}
			} else {
				System.out.println("검색하신 학생이 존재하지 않습니다.");
				return;
			}
		} while (true);
	}
	public void push() throws SQLException {
		cur = head;
		while (cur != null) {
			String sql = "select name from stu where name=?";
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1,cur.getName());
			ResultSet rs = pstmt.executeQuery();
			//이름같은거있는지조회.
			
			boolean match = false;
			if (rs.next()) {
			    // 현재 행의 'name' 컬럼 값을 가져옴
			    String dbName = rs.getString("name");
			    // 데이터베이스에서 조회한 이름과 cur 객체의 이름 비교
			    if (dbName.equals(cur.getName())) {
			        match = true;
			        // 이름이 일치할 때 원하는 작업 수행
			    }
			}
			
			if(!match) {
				try {
					String sql2 = "insert into stu values(null,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql2);
					
					pstmt.setString(1, cur.getName());
					pstmt.setInt(2, cur.getKor());
					pstmt.setInt(3, cur.getEng());
					pstmt.setInt(4, cur.getMat());
					pstmt.setInt(5, cur.getTotal());
					pstmt.setFloat(6, cur.getAvg());
					
					pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			 cur = cur.next;	
		}
		
	}
	public void selectView() {
		System.out.println("1. 이름검색  2. 평균 검색");
		int type = sc.nextInt();

		if (type == 1) {
//			try {
				System.out.println("검색할 학생의 이름을 입력해주세요 : ");
				nameCur(sc.next());

				System.out.println("이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
				cur.print();
				
		} else if (type == 2){
			System.out.println("평균 몇점 이상의 학생을 조회할까요?");
			avgCur(sc.nextInt());
			if (cur == null) {
				System.out.println("해당 평균 이상의 학생이 존재하지 않습니다.");
			}
		} else {
			System.out.println("1 ~ 2 사이의 숫자로 입력해주세요.");
		}
	}
}

