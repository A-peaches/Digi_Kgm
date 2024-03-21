//package Day14;
//
//public class FibonacciEx {
//	
//	int prev1 = 0;
//	int prev2 = 1;
//	int now = 0;
//	public FibonacciEx() {
//		
//	}
//	
//
//	
//	public int fibonacci(int a) {
//
//		if(a == 1) {
//			return 0;
//		} else if(a == 2) {
//			return 1;
//		} else {
//			return fibonacci(a-1) + fibonacci(a-2);
//		}
//		
//	}
//
//	
//	public static void main(String[] args) {
//		FibonacciEx fe = new FibonacciEx();
//		
//		System.out.println(fe.fibonacci(5));
//	}
//}
//
////for (int i =1; i < a; i++) { //5일때, 0,1,2,3,4 반복 
////	if(i == 1) {
////		System.out.print(0 + ", ");
////		now = prev1 + 1; //prev 1 =0
////		prev2 = now; //prev2 = 1
////	} else {
////		now = prev1 + prev2;
////		prev1 = prev2;
////		prev2 = now;
////		
////	}
////System.out.print(now + ", ");
////}
