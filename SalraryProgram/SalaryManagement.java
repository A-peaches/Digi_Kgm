package SalraryProgram;

import java.util.Scanner;

public class SalaryManagement {
	public static void main(String args[]) {
		//나머지는 자동계산, 필수입력 사번,  이름, 직급, 입사일자, 성과지표
		SalaryList salaryList =  new SalaryList();
		int selectNum = 0;
		Scanner sc = new Scanner(System.in);
		
		do {		

			salaryList.mainDisplay();
			selectNum = sc.nextInt();
			switch(selectNum) {
			case 1:
				salaryList.input();
				break;
			case 2:
				salaryList.update();
				break;
			case 3:
				salaryList.search();
				break;
			case 4:
				salaryList.allView();
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
