package Day5;

import java.util.Scanner;

public class AllStudent {
	
	public void addData(Scanner sc ,StudentScoreHasA []stu) {
		System.out.println("이름, 국어, 영어, 수학 점수를 순서대로 입력해주세요.");
		for (int i=0; i<stu.length; i++) {
				System.out.println((i+1)+"번째 학생입니다.");
			
				stu[i] = new StudentScoreHasA();
				stu[i].setName(sc.next());
				stu[i].setKor(sc.nextInt());
				stu[i].setEng(sc.nextInt());
				stu[i].setMat(sc.nextInt());
				
			}
		}
	
	public void viewData(StudentScoreHasA []stu) {
		System.out.println("--------------모든 학생들의 성적표--------------"+'\n'
						   +"이름　　　　국어　　　　영어　　　　수학　　　　총합　　　　평균");
		for(int i= 0; i<stu.length; i++) {
			System.out.print(stu[i].getName()+'\t'+
							 stu[i].getKor()+'\t'+
							 stu[i].getEng()+'\t'+
							 stu[i].getMat()+'\t'+
							 stu[i].getTotal()+'\t'+
							 stu[i].getAvg()+'\n');
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("성적관리 대상 인원을 입력해주세요. ");
		int num = sc.nextInt();
		

		StudentScoreHasA []stu = new StudentScoreHasA[num];
		
		
		//성적입력시, 인원수 각각 이름,국어,수학,영어,총점,평균을 계산하여
		//모두를 출력하는 메소드를 생성한다.
		
		AllStudent all = new AllStudent();
		
		all.addData(sc, stu);
		all.viewData(stu);

	}
}



