package Day5;

import java.util.Scanner;

public class AllStudent {
	private StudentScoreHasA stu;//성적관리 대상 학생 수 
	private StudentScoreHasA []stuArray; //여러명의 학생을 담을 배열.
	private int num; // 담은 학생 수를 저장할 변수.
	Scanner sc = new Scanner(System.in);
	
	
	//Constructor 
	//모든 학생을 관리할 수 있는 객체 생성시, 모든학생들을 담을 sutArray를 생성한다.
	//매개변수 생성자는 허용되지않는다.
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
		System.out.println("==============학생 성적 관리 프로그램 =============="+'\n'+
						   "1. 학생 성적 입력"+'\n'+
						   "2. 학생 성적 수정"+'\n'+
						   "3. 학생 성적 검색"+'\n'+
						   "4. 학생 성적 출력"+'\n'+
						   "5. 프로그램 종료+"+'\n'+
						   "=============================================");
	}
	
	public void addData() {
		System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
				

				stuArray[getNum()] = new StudentScoreHasA();
				stuArray[getNum()].setName(sc.next());
				stuArray[getNum()].setKor(sc.nextInt());
				stuArray[getNum()].setEng(sc.nextInt());
				stuArray[getNum()].setMat(sc.nextInt());
				
				addNum(); //총 학생수 증가. 
		}
	
	public void viewData() {
		System.out.println("============== 모든 학생들의 성적표=============="+'\n'
						   +"이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
		for(int i= 0; i<num; i++) {
			System.out.print(stuArray[i].getName()+'\t'+
							 stuArray[i].getKor()+'\t'+
							 stuArray[i].getEng()+'\t'+
							 stuArray[i].getMat()+'\t'+
							 stuArray[i].getTotal()+'\t'+
							 stuArray[i].getAvg()+'\n');
		}
	}
	
	public void search() {
		
		System.out.println("1. 이름검색  2. 평균 검색");
		int type = sc.nextInt();
		
		System.out.println("검색할 학생 이름을 입력해주세요.");
		String name = sc.next(); 
		int []idx = new int[num];
			
		if (type == 1) {
			
			for (int i=0; i<num; i++) {
				if(stuArray[i].getName().equals(name)){
					idx[0] = i;
				} else 
					continue;
				
			System.out.print(stuArray[idx[0]].getName()+'\t'+
					 stuArray[idx[0]].getKor()+'\t'+
					 stuArray[idx[0]].getEng()+'\t'+
					 stuArray[idx[0]].getMat()+'\t'+
					 stuArray[idx[0]].getTotal()+'\t'+
					 stuArray[idx[0]].getAvg()+'\n');
			} else {
				System.out.println("평균 몇점 이상의 학생을 조회할까요?");
				int avgScore = sc.nextInt();
				
				for (int i=0; i<num; i++) {
					if(stuArray[i].getAvg() >= avgScore ){
						idx[0] = i;
					} else 
						continue;
				
			}
		}

		

		
		

		

	}

	
}



