package Day5;

import java.util.*;

public class AllStudent implements ProgramFunction {
	Scanner sc = new Scanner(System.in);
	Set<StudentScoreHasA> st;
	StudentScoreHasA pointer;
	
// Constructor
// 모든 학생을 관리할 수 있는 객체 생성시, 모든학생들을 담을 sutArray를 생성한다.
// 매개변수 생성자는 허용되지않는다.
	public AllStudent() {
		 st = new HashSet<StudentScoreHasA>();
	}

	public int getSize() {
		return st.size();
	}

	public void mainDisplay() {
		System.out.println("=============== 학생 성적 관리 프로그램 ===============" + '\n' + "1. 학생 성적 입력" + '\n' + "2. 학생 성적 수정"
				+ '\n' + "3. 학생 성적 검색" + '\n' + "4. 학생 성적 출력" + '\n' + "5. 프로그램 종료" + '\n'
				+ "=================================================");
	}

	@Override
	public void addData() {
		System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
		StudentScoreHasA stu = new StudentScoreHasA();
		stu.setName(sc.next()); // 이름과 국어,영어,수학점수를 담은 학생객체를 ArrayList에 추가.
		stu.setKor(sc.nextInt());
		stu.setEng(sc.nextInt());
		stu.setMat(sc.nextInt());
		st.add(stu); //key를 자동으로 박싱하여  Integer로 넣어줌.
		
	}

	@Override
	public void allView() {
		System.out.println("================ 모든 학생들의 성적표 =================" + '\n' + "이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
		for(StudentScoreHasA a : st) {
			a.print();
		}

	}

	public void searchIndex(String name) { // 검색한 학생의 인덱스를 반환해주는 메서드
		for(StudentScoreHasA a : st) {
			if(a.getName().equals(name)) {
				this.pointer = a;
				break;	
			}
		}
	}

//	
	@Override
	public void updateData() {
		int updateNum = 0;
		int prev = 0;
		int close = 0;

		do {
			System.out.println("수정할 학생의 이름을 입력해주세요 : ");
			String name =  sc.next();
			searchIndex(name);
			try {

				while (updateNum != 4) {
					System.out.println("수정할 과목을 입력해주세요." + '\n' + "1. 국어" + '\t' + "2. 영어 " + '\n' + "3. 수학" + '\t'
							+ "4. 다른 학생 수정" + '\n');
					updateNum = sc.nextInt();
					switch (updateNum) {
					case 1:
						System.out.println("수정할 점수를 입력해주세요.");
						prev = pointer.getMat();
						pointer.setKor(sc.nextInt());
						System.out.println(
								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + pointer.getKor());
						return;
					case 2:
						System.out.println("수정할 점수를 입력해주세요.");
						prev = pointer.getEng();
						pointer.setEng(sc.nextInt());
						System.out.println(
								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + pointer.getEng());
						return;
					case 3:
						System.out.println("수정할 점수를 입력해주세요.");
						prev = pointer.getMat();
						pointer.setMat(sc.nextInt());
						System.out.println(
								"수정이 완료되었습니다." + '\n' + "수정 전 : " + prev + '\t' + "수정 후 : " + pointer.getMat());
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
			} catch(Exception e) {
				System.out.println("검색하신 학생이 존재하지 않습니다.");
				return;
			}
		} while (true);
	}

	public void selectView() {
		System.out.println("1. 이름검색  2. 평균 검색");
		int type = sc.nextInt();

		if (type == 1) {
				System.out.println("검색할 학생의 이름을 입력해주세요 : ");
				searchIndex(sc.next());

				try {
				System.out.println("이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
				pointer.print();
				} catch(Exception e) {
					System.out.println("검색한 학생이 존재하지 않습니다.");
				}
				
		} else if (type == 2) {
			System.out.println("평균 몇점 이상의 학생을 조회할"
					+ "까요?");
			int avgScore = sc.nextInt();

			for(StudentScoreHasA a : st) {
				if(a.getAvg() >= avgScore) {
					a.print();
				} 
			}
		} else {
			System.out.println("1 ~ 2 사이의 숫자로 입력해주세요.");
		}
	}
}

//---------------------여기는 ArrayList입니당 ------------------------
//public class AllStudent implements ProgramFunction{
//	Scanner sc = new Scanner(System.in);
//	List<StudentScoreHasA> st;
//	 
//	// Constructor
//	// 모든 학생을 관리할 수 있는 객체 생성시, 모든학생들을 담을 sutArray를 생성한다.
//	// 매개변수 생성자는 허용되지않는다.
//	public AllStudent() {
//		st = new ArrayList<StudentScoreHasA>();
//	}
//
//	public int getSize() {
//		return st.size();
//	}
//
//
//
//	public void mainDisplay() {
//		System.out.println("=============== 학생 성적 관리 프로그램 ===============" 
//				+ '\n' + "1. 학생 성적 입력" + '\n' + "2. 학생 성적 수정"
//				+ '\n' + "3. 학생 성적 검색" 
//				+ '\n' + "4. 학생 성적 출력" 
//				+ '\n' + "5. 프로그램 종료" + '\n'
//				+ "=================================================");
//	}
//
//	@Override
//	public void addData() {
//		System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
//		StudentScoreHasA stu = new StudentScoreHasA();
//		stu.setName(sc.next());	//이름과 국어,영어,수학점수를 담은 학생객체를 ArrayList에 추가.
//		stu.setKor(sc.nextInt());
//		stu.setEng(sc.nextInt());
//		stu.setMat(sc.nextInt());
//		st.add(stu);
//	}
//	
//	
//
//
//	@Override
//	public void allView() {
//		System.out.println("================ 모든 학생들의 성적표 =================" + 
//						'\n' + "이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
//		
//		for (StudentScoreHasA i : st) {
//			i.print();
//
//		}
//	
//	}
//
//		
////		for (int i = 0; i < num; i++) {
////			System.out.print(stuArray[i].getName() + '\t' 
////					+ stuArray[i].getKor() + '\t' 
////					+ stuArray[i].getEng() + '\t'
////					+ stuArray[i].getMat() + '\t' 
////					+ stuArray[i].getTotal() + '\t' 
////					+ stuArray[i].getAvg() + '\n');
////		}
//	
//
//	public int searchIndex(String name) { //검색한 학생의 인덱스를 반환해주는 메서드
//		int idx = 0;
//		for (int i = 0; i<st.size(); i++) {
//			if(st.get(i).getName().equals(name)) {
//				idx = i;
//				break;
//			} else {
//				idx = -1;
//			}
//		
//		}
//		return idx;
//	}
////		
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
//	public void selectView() {
//		System.out.println("1. 이름검색  2. 평균 검색");
//		int type = sc.nextInt();
//
//		if (type == 1) {
//			try {
//				System.out.println("검색할 학생의 이름을 입력해주세요 : ");
//				int idx = searchIndex(sc.next());
//
//				System.out.println("이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
//				st.get(idx).print();
//			} catch (IndexOutOfBoundsException ie) {
//				System.out.println("검색하신 학생이 존재하지 않습니다.");
//			}
//		} else if (type == 2){
//			System.out.println("평균 몇점 이상의 학생을 조회할까요?");
//			int avgScore = sc.nextInt();
//
//			for (int i = 0; i < st.size(); i++) {
//				if (st.get(i).getAvg() >= avgScore) {
//					st.get(i).print();
//				}
//			}
//		} else {
//			System.out.println("1 ~ 2 사이의 숫자로 입력해주세요.");
//		}
//	}
//}
// ----------------------------------------------------------------------------


//		int []idx = new int[num]; //idx배열 생
//		int accIdx = 0;
//			
//		if (type == 1) {
//			System.out.println("검색할 학생 이름을 입력해주세요."); 
//			String name = sc.next();  //name에 입력값 
//			
//			for (int i=0; i<num; i++) {
//				if(stuArray[i].getName().equals(name)){
//					selectView(i);
//				} else
//					continue; 
//				
//
//			}
//				
//			} 
//				
//			}
//		}
//	}
//
//	public void updateData() {
//	
//				