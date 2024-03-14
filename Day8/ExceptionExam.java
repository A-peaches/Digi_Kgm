//package Day8;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class ExceptionExam extends Exception{
//	public static void main(String[] args) {
//
//		Scanner sc = new Scanner(System.in);
//
//		try { // 예상되는 예외를 인식.
//			int num = sc.nextInt();
//			System.out.println(3 / num);
//			
//		} catch (ArithmeticException ae) {
//			System.out.println("0으로 입력하지마!");
//			
//			return;
////		} catch (InputMismatchException ie) {
//		} catch (Exception in) {// 상위인 Exception은 제일 아래, 하위는 위에서 처리.
//			System.out.println("문자로 입력하지마!");
//		}  finally {  // return을 만나더라도 , Exception을 만나더라도,
//					  // 무엇과 만나도 finally를 무조건 찾아감.
//	
//
//		System.out.println("Bye");
//		}
//	}
//
//}
