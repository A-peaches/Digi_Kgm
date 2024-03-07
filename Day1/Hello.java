/*
 *  java는 100% 상속
 *  java는 100% 객체지향 언어이다 -> 무조건 객체를 사용. -> class
 *  
 *  시작과 끝을 담당하는 함수 -> main 함수
 *  함수 -> function -> method
 */
// 패키지
// 임포트
package Day1;


import java.lang.*;
import java.util.Scanner;

public class Hello {
	public static void main(String args[]) { //// 프로그램을 시작하고 끝내줌.
		
		//사각형 접은 횟수와 사각형 개수
		int square = 1;
		int count = 0;
		while(square<500) {
			square *= 2;
			count++;
		}
		System.out.println("사각형을 접은 횟수는 "+count+"번이며, 사각형의 수는 "+square+"입니다.");
		
		//구구단을 외자
		int i = 1;
		while(i<10) {
			int j = 2;
			while(j<10) {
				System.out.print(j+"*"+i+"="+(j*i)+'\t');
				j++;
			}
			i++;
			System.out.println();
		}
	
	}
}

////이중포문을 활용한 구구단 만들기
////2*1 .... 2*9 나온후에 넘어가서 3*1....
//
//for (int i = 1; i < 10; i++) {
//	for (int j = 2; j < 10; j++) {
//		
//			System.out.print(j + "*" + i + "=" + (i * j) + '\t');
//		}
//		System.out.println();
//	}
//

//접은 횟수와 사각형의 수를 for문을 사용하여 출력. 사각형의 수가 500보다 작을때까지.

		// 0번접었을땐 1개! 1번접었을땐 2개! 2번접었을땐 4개!
		// 1 * 2 = 
//		int square = 1;
//		int i =0;
//		
//		for(i=0; square<500; i++) {
//			square <<= 1;
//			//square *= 2;
//			//square = square * 2;
//		}
		
		// 1번째 1번접으면 2개! 
		// 2번째 2번접으면 4개!
		
//		System.out.println("사각형을 접은 횟수는 "+i+"번이며, 사각형의 수는 "+square+"입니다.");
//
//
//		/*
//		 * 사칙 연산 계산기 ( + , - , * , / ) 3 + 4 = 7
//		 */
//
//		Scanner sc;
//		sc = new Scanner(System.in);
//
//		System.out.println("==========사칙 연산 계산기===========");
//		System.out.print("num1의 값:");
//		int num1 = sc.nextInt();
//		System.out.print("+,-,*,/ 중 택1하여 입력해주세요:");
////		char operator = sc.next().charAt(0);
//		String operator = sc.next();
//		System.out.print("num2의 값:");
//		int num2 = sc.nextInt();
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
//	}
//
//}

//switch(operator) {
//case"+":
//	System.out.println("num1+num2="+(num1+num2));
//	break;
//case"-":
//	System.out.println("num1-num2="+(num1-num2));
//	break;
//case"*":
//	System.out.println("num1*num2="+(num1*num2));
//	break;
//case"/":
//	System.out.println("num1/num2="+(num1/num2));
//	break;
//default:
//	System.out.println("올바른 연산자를 입력해주세요!");
//	System.out.println("사칙연산 계산기를 종료합니다.");
//}

//a와b를 비교하여 a보다 b를 비교하는 문구를 출력.
//Scanner sc;
//sc = new Scanner(System.in);
//
//System.out.print("a의 값 : ");
//int a = sc.nextInt();
//System.out.print("b의 값 : ");
//int b = sc.nextInt();
//
//if(a>b) {
//	System.out.println("a: "+a+"가 b: "+b+"보다 큽니다.");
//}else if(a<b) {
//	System.out.println("b: "+b+"가 a: "+a+"보다 큽니다.");
//}else {
//	System.out.println("a: "+a+"와 b: "+b+"는 같습니다.");
//}
//

// datatype 변수명;
// 변수명 = 값;

//int num;
//num =10;
//int num1;
//num1 =10;
//num1 += 1;
//
//char ch = 'A'; // "" 는 문자열, ''는 문자.
//System.out.println(num1);
//System.out.println((int)ch);
//
//
//Hello hello; // hello 는 reference 변수 객체를 접근할 수 있는 변수 = reference 변수
//hello = new Hello(); //객체 생성
//
//int a = 12; // 12의 이진 표현: 1100
//int b = 9;  //  9의 이진 표현: 1001
//int result = a & b; // 결과: 1000, 이진수로 8
//
//System.out.println(result); // 출력: 8

//System.out.println("Hello");
//System.out.println(333);
//
//System.out.print("Hello" + 333);
//System.out.print(333);

//Scanner sc;
//sc = new Scanner(System.in);
//
//String str= sc.next();
//int num = sc.nextInt();
////next는 return타입. 함수앞에 사용.
//System.out.println(str);
//System.out.println(num);
//
//
