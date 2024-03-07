//package Day4;
//
//import java.util.Scanner;
//
//public class Calculator_Exam {
//	public static void main(String[] args) {
//		char key = 'a';
//
//		Calculator_Exam cal = new Calculator_Exam();
//		Scanner sc = new Scanner(System.in);
//		
////		Calculator_Exam cal2 = new Calculator_Exam(3,'+',4);
////		cal2.result();
//		
//
//		do {
//			System.out.println("첫번째 값을 입력해주세요.");
//			cal.setNum1(sc.nextInt());
//			System.out.println("연산자를 입력해주세요.");
//			cal.setOp(sc.next().charAt(0));
//			System.out.println("두번째 값을 입력해주세요.");
//			cal.setNum2(sc.nextInt());
//
//
//			cal.result();
//
////			cal.setNum1(3);
////			cal.setNum2(4);
////			cal.setOp('+');
//
//			System.out.println("계속하려면 Y를 눌러주세요.");
//			key = sc.next().charAt(0);
//		} while (key == 'Y' | key == 'y');
//
//	}
//
//	// 필드
//	private int num1;
//	private int num2;
//	private char op;
//	private double result;
//
//	// 생성자
//	public Calculator_Exam() {
//		System.out.println("=======계산기 프로그램을 작동합니다=======");
//	}
//	
//	public Calculator_Exam(int a,char c, int b) {
//		this.num1 = a;
//		this.num2 = b;
//		this.op = c;
//	}
//
//	public void setNum1(int num) {
//		num1 = num;
//	}
//
//	public void setNum2(int num) {
//		num2 = num;
//	}
//
//	public void setOp(char operator) {
//		op = operator;
//	}
//
//	public int getNum1() {
//		System.out.println("현재 첫번째 입력값은" + num1 + "입니다.");
//		return num1;
//	}
//
//	public int getNum2() {
//		System.out.println("현재 두번째 입력값은" + num2 + "입니다.");
//		return num1;
//	}
//
//	public int getOp() {
//		System.out.println("현재 연산자는" + op + "입니다.");
//		return num1;
//	}
//
////		// 숫자입력
////		public int insert(Scanner sc) {
////			int num;
////			num = sc.nextInt();
////			return num;
////		}
////
////		// 부호입력
////		public char insert_char(Scanner sc) {
////			char op;
////			op = sc.next().charAt(0);
////			return op;
////		}
//
//	// <계산과정>
//	// 사칙연산 각각 구현.
//	// result함수. 매개변수는 3개를 받으며, 부호에 따른 함수호출 후 결과값 출력.
//	// static method
////덧셈
//	public void sum() {
//		result = num1 + num2;
//	}
//
////뺄셈
//	public void sub() {
//		result = num1 - num2;
//	}
//
////곱셈
//	public void mul() {
//		result = num1 * num2;
//	}
//
////나눗셈
//	public void div() {
//		result = (double) num1 / num2;
//	}
//
//// 결과출력 함수                                                                                                                                                                                                                                                                                                                         
//	public void result() {
//
//		// op에따른 계산 함수 호출
//		switch (op) {
//		case '+':
//			sum();
//			break;
//		case '-':
//			sub();
//			break;
//		case '*':
//			mul();
//			break;
//		case '/':
//			div();
//			break;
//		default:
//			System.out.println("계산 에러! 올바른 연산자를 사용해주세요.");
//		}
//		System.out.println(num1 + " " + op + " " + num2 + " = " + result);
//		System.out.println("계산을 종료합니다.");
//	}
//}