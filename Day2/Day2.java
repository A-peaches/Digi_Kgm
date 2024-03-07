//package Day2;
//
//import java.util.Scanner;

//public class Day2 {
//	public static void main(String arg[]) {
//		Scanner sc;
//		sc = new Scanner(System.in);
//		String ans;
//		int num1, num2;
//
//		do {
//		System.out.println("==========사칙 연산 계산기===========");
//		System.out.print("num1의 값:");
//		num1 = sc.nextInt();
//		System.out.print("+,-,*,/ 중 택1하여 입력해주세요:");
//		String operator = sc.next();
//		System.out.print("num2의 값:");
//		num2 = sc.nextInt();
//
//
//		System.out.println("===============결과값===============");
//
//		if (operator.equals("+")) {
//			System.out.println(num1 + "" + operator + "" + num2 + "=" + (num1 + num2));
//		} else if (operator.equals("-")) {
//			System.out.println(num1 + "" + operator + "" + num2 + "=" + (num1 - num2));
//		} else if (operator.equals("*")) {
//			System.out.println(num1 + "" + operator + "" + num2 + "=" + (num1 * num2));
//		} else if (operator.equals("/")) {
//			System.out.println(num1 + "" + operator + "" + num2 + "=" + ((double) num1 / num2));
//		} else {
//			System.out.println("올바른 부호를 입력해주세요!!");
//		}
//		
//		System.out.println("계속 하시겠습니까? ('Y'or'y'시 계속, 다른 키 입력시 종료됩니다.)");
//		ans = sc.next();
//		
//		}while(ans.equals("Y") || ans.equals("y"));
//		
//		System.out.println("사칙 연산 계산기 프로그램을 종료합니다! ");
//	}
//}
