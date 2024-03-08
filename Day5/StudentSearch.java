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
			case 3:
				allstu.search();
				break;
			case 4:
				allstu.viewData();
				break;
			default:
				System.out.println("프로그램을 종료합니다.");
				num = 5;
				break;
		}
		
			
		}while(num != 5);
	}
}
