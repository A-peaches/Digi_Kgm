//package Day14;
//
//import java.util.Scanner;
//
//public class FactorialEx {
//	// 1. 반복문으로 짠다
//	// 2. 재귀함수로 짠다.
//	//Scanner로 입력받는다.
//	
//	int result = 1;
//	Scanner sc;
//	public FactorialEx() {
//		sc = new Scanner(System.in);
//	}
//	public int ftFor(int a) { // 반복문~
//
//		for(int i = a; i != 0; i--) {
//			System.out.print( i +" * ");
//			result = result * i;
//
//			/* 5
//			 * 5*4
//			 * 20 *3
//			 * 60 * 2
//			 * 120 * 1
//			 */
//		}
//		System.out.println("= " + result);
//		return result;
//	}
//	
//	public int ftRecursive(int a) {
//		
//		if (a > 0) {
//			//5부터시작.. 0이면 탈출
//			System.out.print( a +" * ");
//			result = a * ftRecursive(a-1);  
//		} 
//		
//		return result;
//	}
//	
//	public void getResult() {
//		System.out.println("= "+result);
//	}
//	
//
//	
//	public int input() {
//		System.out.println("=====팩토리얼 구하기=====");
//		System.out.println("   숫자를 입력해주세요 !  ");
//		int input = sc.nextInt();
//		
//		return input;
//	}
//	
//	
//	public static void main(String[] args) {
//		// ex) 5면, 5 * 4 * 3 * 2 * 1 
//		FactorialEx fe = new FactorialEx();
//		fe.ftFor(fe.input());
//		fe.ftRecursive(fe.input());
//		fe.getResult();
//		
//	}
//}
