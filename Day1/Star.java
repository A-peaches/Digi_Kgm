package Day1;
import java.util.Scanner;
import java.math.*;
public class Star {
	public static void main(String arge[]) {
// 

		Scanner sc = new Scanner(System.in);
		Star star = new Star();
		char ans = 'a';
		
		System.out.println("******* Drawing Star Program *******");
		System.out.print("별을 찍을 높이를 정해주세요. 홀수만 가능합니다 : ");
		int i = 0;
		int height = sc.nextInt();
		
		System.out.println("=== 만들고 싶은 별의 모양을 골라주세요. ===");
		System.out.println( "1. * * *     2. *         3.    *"+'\n'
						+   "   * * *        * *           * *"+'\n'
						+   "   * * *        * * *       * * *"+'\n'+'\n'
						+ "4.   *       5.   *"+'\n'
						+ "   * * *        * * *" +'\n'
						+ " * * * * *    * * * * *"+'\n'
						+ "                * * *" + '\n'
						+ "                  *");
		
	do {
		switch(sc.nextInt()) {
			case 1:
				star.star1(height);
			break;
			case 2:
				star.star2(height);
				break;
			case 3:
				star.star3(height);
				break;
			case 4:
				star.star4(height);
				break;
			case 5:
				star.star5(height);
				break;
			default: 
				System.out.println("1부터 5사이의 숫자로 입력해주세요!!");
				System.out.println("Drawing Star program을 종료합니다.");
		}
		
		System.out.println("계속 만드실거면 y를 입력하신후, 별의 모양을 골라주세요.");
		ans = sc.next().charAt(0);
	}while(ans=='y' | ans =='Y');
	
	
	System.out.println("Drawing Star program을 종료합니다.");
	}

	
		public void star1(int height){
//			if (height % 2 != 0) {
			int i = 0;
			while (i < height) {
				int j = 0;
				while (j < height) {
					System.out.print("＊" + "");
					j++;
				}
				System.out.println("");
				i++;
			}
		}

		
		//2번 문제
		// * 
		// * *
		// * * *
		
		public void star2(int height){
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < i+1; j++) {
					System.out.print("＊" + "");
				}
				System.out.println();

			}
		}
		
		//3번 문제
		//    * 
		//  * *
		//* * *  1. 공백2 별1 , 공백1별2, 공백0별3
		

//		
		public void star3(int height) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < height-i-1; j++) {
					System.out.print("　");
				}
				
				for (int k = 0; k < i+1; k++) {
					System.out.print("＊");
				}
				System.out.println();
			}
		}
	
//		//4번 문제
		//  *   1. 만약 3줄이면 3번째별 나머지공백.
		// ***	2. 
		//*****
		
		public void star4(int height) {
		for (int i = 1; i <height + 1; i++) {

			for (int j = 1; j < height - i + 1; j++) {
		        System.out.print("　"); // i가 1일떄,  j < 3/  1,2 반복... 공백2개 / 
		        // i가 2일때,  j<2 1 반복.
		        // i가 3일때,  j<1 실행 x
		    }
			
		    for (int k = 1; k < 2*i ; k++) {
		        System.out.print("＊");
		        // i가 1일때, k<2 , 1번반복.
		        // i가 2일때, k<4 , 1,2,3, 반복.. 별 3개.
		        // i가 3일때, k<6 , 1,2,3,4,5 반복.. 별 5개.
		    }
		    
		    System.out.println();
		}
		}
		
		//5번문제
		//   *      1.공백3 / 별 1 = 4개 반복횟수/ 공백3번 별1번, 공백1번.
		//  ***		2.공백1/  별 3 = 4개
		// *****	3.공백0/  별 5 = 5개 
		//  ***		4.공백1/  별 3 = 4개
		//   *		5.공백3/  별 1 = 4개  
//		int height = 5; // 다이아몬드의 높이, 홀수
//		int med = height / 2 + 1; // 중앙 줄의 인덱스
//
//		// 상단부 출력
//		for (int i = 1; i <= med; i++) {
//			for (int j = 1; j <= med - i; j++) {
//				System.out.print(" ");
//			}
//			for (int j = 1; j <= 2 * i - 1; j++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
//
//		// 하단부 출력
//		for (int i = med - 1; i >= 1; i--) {
//			for (int j = 1; j <= med - i; j++) {
//				System.out.print(" ");
//			}
//			for (int j = 1; j <= 2 * i - 1; j++) {
//				System.out.print("*");
//			}
//			System.out.println();
//		}
		
//		   //6번 7번 같이

		public void star5(int height) {
	        int med = height / 2; // 중앙 인덱스, 0부터 시작하는 인덱스 기준

	        for (int i = 0; i < height; i++) { // 전체 높이를 기준으로 각 줄 순회
	            for (int j = 0; j < height; j++) { // 해당 줄에 대해 공백 또는 별 출력
	                // 상단부 조건: 현재 줄이 중앙 인덱스보다 작거나 같을 경우
	                // 하단부 조건: 현재 줄이 중앙 인덱스보다 클 경우
	                // 두 조건 모두에서 공백 또는 별을 출력할지 결정
	                if (Math.abs(j - med) <= i && i <= med || // 상단부 조건
	                    i > med && Math.abs(j - med) < height - i) { // 하단부 조건
	                    System.out.print("＊");
	                } else {
	                    System.out.print("　");
	                }
	            }
	            System.out.println(); // 줄바꿈
	        }
		}
//	}
//
//}
}
	