package StudentLink;

import java.util.*;

import Day14.LinkNode;


// implements ProgramFunction
public class AllStudent{
	Scanner sc = new Scanner(System.in);
	Student newSt;
	Student head;
	Student cur;
	Student del;
	
	// Constructor
	// 모든 학생을 관리할 수 있는 객체 생성시, 모든학생들을 담을 stuArray를 생성한다.
	// 매개변수 생성자는 허용되지않는다.
	public AllStudent() {
	}

	


	public void mainDisplay() {
		System.out.println("=============== 학생 성적 관리 프로그램 ===============" 
				+ '\n' + "1. 학생 성적 입력" + '\n' + "2. 학생 성적 수정"
				+ '\n' + "3. 학생 성적 검색" 
				+ '\n' + "4. 학생 성적 출력" 
				+ '\n' + "5. 프로그램 종료" + '\n'
				+ "=================================================");
	}

//	@Override
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
			nullPrev();
			System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
			newSt = new Student();
			newSt.setName(sc.next());
			newSt.setKor(sc.nextInt());
			newSt.setEng(sc.nextInt());
			newSt.setMat(sc.nextInt());
			// 1개만있으면, cur가 1을가리키면, newSt = cur.next
			// cur.next = newSt
			newSt.next = cur.next;
			cur.next = newSt;
		}
	}
	
	public void nullPrev() {
		//null이전값 탐색. 만약 첫번쨰의 next가 null이면, 두번째에 넣어야함.
		cur = head;
		while (cur.next != null) {
			cur = cur.next;
		}
	}


//	@Override
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
		while (cur.next != null) { 
			if(cur.getName().equals(name)) {
				break;
			}else {
				cur = cur.next; 
			}
			//이름이다르면, 다음 노드로 넘어간다.
		}
	}
//		
//	@Override
//	public void updateData() {
//		int updateNum = 0;
//		int prev = 0;
//		int close = 0;
//
//		do {
//			System.out.println("수정할 학생의 이름을 입력해주세요 : ");
//			int idx = searchIndex(sc.next());
//
//			if (idx != -1) {
//
//				st.get(idx).print();
//
//				while (updateNum != 4) {
//					System.out.println("수정할 과목을 입력해주세요." + '\n' + "1. 국어" + '\t' + "2. 영어 " + '\n' + "3. 수학" + '\t'
//							+ "4. 다른 학생 수정" + '\n');
//					updateNum = sc.nextInt();
//					switch (updateNum) {
//					case 1:
//						System.out.println("수정할 점수를 입력해주세요.");
//						prev = st.get(idx).getKor();
//						st.get(idx).setKor(sc.nextInt());
//						System.out.println(
//								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + st.get(idx).getKor());
//						return;
//					case 2:
//						System.out.println("수정할 점수를 입력해주세요.");
//						prev = st.get(idx).getEng();
//						st.get(idx).setEng(sc.nextInt());
//						System.out.println(
//								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + st.get(idx).getEng());
//						return;
//					case 3:
//						System.out.println("수정할 점수를 입력해주세요.");
//						prev = st.get(idx).getMat();
//						st.get(idx).setMat(sc.nextInt());
//						System.out.println(
//								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + st.get(idx).getMat());
//						return;
//					case 4:
//						break; // 다른학생선택시 switch문을 벗어난다.
//					default:
//						System.out.println("1 ~ 4 사이의 숫자로 입력해주세요.");
//					}
//
//					if (updateNum == 4) {
//						break; // switch문을 벗어난 뒤 for문을 벗어난다.
//					}
//				}
//			} else {
//				System.out.println("검색하신 학생이 존재하지 않습니다.");
//				return;
//			}
//		} while (true);
//	}
//
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

		} else {
			System.out.println("1 ~ 2 사이의 숫자로 입력해주세요.");
		}
	}
}

