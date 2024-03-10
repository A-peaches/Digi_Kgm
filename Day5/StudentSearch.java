package Day5;

import java.util.Scanner;

public class StudentSearch {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		AllStudent allstu= new AllStudent(); 
		int num =0;
		
		do {
			allstu.mainDisplay();
			num = sc.nextInt();
			
		switch(num) {
			case 1:
				allstu.addData();
				break;
			case 2:
				allstu.updateData();
				break;
			case 3:
				allstu.search();
				break;
			case 4:
				allstu.viewData();
				break;
			case 5:
				num = 5;
				break;
			default:
				System.out.println("1 ~ 4의 숫자로 입력해주세요.");
				num = 5;
				break;
		}
		
		
			
		}while(num != 5);
		System.out.println("프로그램을 종료합니다.");
		
		
	}
}
