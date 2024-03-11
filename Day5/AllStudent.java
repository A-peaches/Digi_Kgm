package Day5;

import java.util.Scanner;

public class AllStudent {
	private StudentScoreHasA stu;// 성적관리 대상 학생 수
	private StudentScoreHasA[] stuArray; // 여러명의 학생을 담을 배열.
	private int num; // 담은 학생 수를 저장할 변수.
	Scanner sc = new Scanner(System.in);
	
	// Constructor
	// 모든 학생을 관리할 수 있는 객체 생성시, 모든학생들을 담을 sutArray를 생성한다.
	// 매개변수 생성자는 허용되지않는다.
	public AllStudent() {
		stuArray = new StudentScoreHasA[100];
	}

	public int getNum() {
		return num;
	}

	public void addNum() {
		this.num = this.num + 1;
	}

	public void mainDisplay() {
		System.out.println("==============학생 성적 관리 프로그램 ==============" + '\n' + "1. 학생 성적 입력" + '\n' + "2. 학생 성적 수정"
				+ '\n' + "3. 학생 성적 검색" 
				+ '\n' + "4. 학생 성적 출력" 
				+ '\n' + "5. 프로그램 종료" + '\n'
				+ "===============================================");
	}

	public void addData() {
		System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");

		stuArray[getNum()] = new StudentScoreHasA();
		stuArray[getNum()].setName(sc.next());
		stuArray[getNum()].setKor(sc.nextInt());
		stuArray[getNum()].setEng(sc.nextInt());
		stuArray[getNum()].setMat(sc.nextInt());

		addNum(); // 총 학생수 증가.
	}

	public void viewData() {
		System.out.println("============== 모든 학생들의 성적표==============" + '\n'
					+ "이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
		for (int i = 0; i < num; i++) {
			System.out.print(stuArray[i].getName() + '\t' 
					+ stuArray[i].getKor() + '\t' 
					+ stuArray[i].getEng() + '\t'
					+ stuArray[i].getMat() + '\t' 
					+ stuArray[i].getTotal() + '\t' 
					+ stuArray[i].getAvg() + '\n');
		}
	}
	
	public void selectView(int i) {
		System.out.println( "이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
		System.out.print(stuArray[i].getName()+'\t'+
				 stuArray[i].getKor()+'\t'+
				 stuArray[i].getEng()+'\t'+
				 stuArray[i].getMat()+'\t'+
				 stuArray[i].getTotal()+'\t'+
				 stuArray[i].getAvg()+'\n');
	}
	
	public void search() {
		
		System.out.println("1. 이름검색  2. 평균 검색");
		int type = sc.nextInt();
		int []idx = new int[num]; //idx배열 생
		int accIdx = 0;
			
		if (type == 1) {
			System.out.println("검색할 학생 이름을 입력해주세요."); 
			String name = sc.next();  //name에 입력값 
			
			for (int i=0; i<num; i++) {
				if(stuArray[i].getName().equals(name)){
					selectView(i);
				} else
					continue; 
				

			}
				
			} else if (type == 2){
				
				System.out.println("평균 몇점 이상의 학생을 조회할까요?");
				int avgScore = sc.nextInt();
				
				for (int i=0; i<num; i++) {
					if(stuArray[i].getAvg() >= avgScore ){
						selectView(i);
					} else 
						continue;
				
			}
		}
	}

	public void updateData() {
		int updateNum = 0;
		int prev = 0;
		int close = 0;
		
		do {
			updateNum = 0;
			String name = "";
			System.out.println("점수를 수정할 학생 이름을 입력해주세요.");
			name = sc.next();

			for (int i = 0; i < num ; i++) {
					
				if (stuArray[i].getName().equals(name)) {
					selectView(i);
					
					while (updateNum != 4) {
						System.out.println("수정할 과목을 입력해주세요." + '\n' 
										  + "1. 국어" + '\t' + "2. 영어 " + '\n' 
										  + "3. 수학" + '\t' + "4. 다른 학생 수정"+'\n');
						updateNum = sc.nextInt();
						switch (updateNum) {
						case 1:
							System.out.println("수정할 점수를 입력해주세요.");
							prev = stuArray[i].getKor();
							stuArray[i].setKor(sc.nextInt());
							System.out.println( "수정이 완료되었습니다." + '\n' 
												+ "수정 전 : " + prev + '\t' 
												+ "수정 후 : " + stuArray[i].getKor());
							return;
						case 2:
							System.out.println("수정할 점수를 입력해주세요.");
							prev = stuArray[i].getEng();
							stuArray[i].setEng(sc.nextInt());
							System.out.println( "수정이 완료되었습니다." + '\n' 
									+ "수정 전 : " + prev + '\t' 
									+ "수정 후 : " + stuArray[i].getEng());
							return;
						case 3:
							System.out.println("수정할 점수를 입력해주세요.");
							prev = stuArray[i].getMat();
							stuArray[i].setMat(sc.nextInt());
							System.out.println( "수정이 완료되었습니다." + '\n' 
									+ "수정 전 : " + prev + '\t' 
									+ "수정 후 : " + stuArray[i].getMat());
							return;
						case 4:
							break; // 	다른학생선택시 switch문을 벗어난다.
						default:
							System.out.println("1 ~ 4 사이의 숫자로 입력해주세요.");
						}
						
						if (updateNum == 4) {
							break; // switch문을 벗어난 뒤 for문을 벗어난다.
						}
					}
				} else
					continue;
			}
		} while (true);
	}
}
				