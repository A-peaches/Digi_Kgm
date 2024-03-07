package Day2;

import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Calculator calc = new Calculator();
		
		char my_continue;

		do {
			System.out.println("첫번째 숫자를 입력해주세요:");
			int num1 = calc.insert(sc);

			System.out.println("연산자를 입력해주세요:");
			char op = calc.insert_char(sc);

			System.out.println("두번째 숫자를 입력해주세요:");
			int num2 = calc.insert(sc);

			calc.result(num1, op, num2);

			my_continue = calc.insert_char(sc);

		} while (my_continue == 'Y' | my_continue == 'y');

	}
	// <입력과정>
	// 1. 입력하세용
	// 2. 사용가자입력
	// 3. 입력한 num를 변수에 저장

// 숫자입력
	public int insert(Scanner sc) {
		int num;
		num = sc.nextInt();
		return num;
	}

// 부호입력
	public char insert_char(Scanner sc) {
		char op;
		op = sc.next().charAt(0);
		return op;
	}

	// <계산과정>
	// 사칙연산 각각 구현.
	// result함수. 매개변수는 3개를 받으며, 부호에 따른 함수호출 후 결과값 출력.
	// static method
//덧셈
	public int plu(int num1, int num2) {
		int result = num1 + num2;
		return result;
	}

//뺄셈
	public int min(int num1, int num2) {
		int result = num1 - num2;
		return result;
	}

//곱셈
	public int mul(int num1, int num2) {
		int result = num1 * num2;
		return result;
	}

//나눗셈
	public double div(int num1, int num2) {
		double result = (double) num1 / num2;
		return result;
	}

// result 함수                                                                                                                                                                                                                                                                                                                         
	public void result(int num1, char op, int num2) {
		double result = 0;
		Calculator calc = new Calculator();

		// op에따른 계산 함수 호출
		switch (op) {
		case '+':
			result = calc.plu(num1, num2);
			break;
		case '-':
			result = calc.min(num1, num2);
			break;
		case '*':
			result = calc.mul(num1, num2);
			break;
		case '/':
			result = calc.div(num1, num2);
			break;
		}

		System.out.println(num1 + " " + op + " " + num2 + " = " + result);
		System.out.println("계속하시려면 Y를 눌러주세요.");
		
	}

}
