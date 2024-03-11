package SalraryProgram;

import java.util.Scanner;

public class SalaryManagement {
	public static void main(String args[]) {
		//나머지는 자동계산, 필수입력 사번,  이름, 직급, 입사일자, 성과지표
		SalaryList salaryList =  new SalaryList();
		int selectNum = 0;
		Scanner sc = new Scanner(System.in);
		
		do {		
			salaryList.mainDisplay(); // 초기화면
			selectNum = sc.nextInt();
			switch(selectNum) {
			case 1:
				salaryList.input(); //입력메서드
				break;
			case 2:
				salaryList.update(); //수정메서드
				break;
			case 3:
				salaryList.search(); //검색메서드
				break;
			case 4:
				salaryList.allView(); //조회메서드
				break;
			case 5:
				selectNum = 5;
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("1 ~ 5 사이의 숫자로 입력해주세요.");
				break;
			}
		}while(selectNum != 5);
	}
}
