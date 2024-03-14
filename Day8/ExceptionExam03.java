//package Day8;
//	
//class Dog{
//		private int age;
//		private int height;
//		private String nameCode;
//		
//		public Dog() {
//			System.out.println("멍!!!!");
//		}
//		
//		public Dog(int age) {
//			this.age = age;
//			System.out.println("멍!!!!");
//		}
//		
//		
//		public void speak() {
//			System.out.println("멍멍!!");
//		}
//		
//		public void setAge(int age) {
//			this.age = age;
//		}
//		
//		public int getAge() {
//			return age;
//		}
//		
//		public void setHeight(int height) {
//			this.height = height;
//		}
//		
//		public String getNameCode() {
//			return nameCode;
//		}
//		
//		public void setNameCode(String nameCode) {
//			this.nameCode = nameCode;
//		}
//	}
//
//
//public class ExceptionExam03 {
//		
//	public static void main(String[] args) {
//		//예외처리 잡아내기~!
//		
//		Dog dogs[] = new Dog[5];
//		dogs[0] = new Dog();
//		dogs[0].setAge(1);
//		dogs[0].setNameCode("피망");
//		
//		try {
//		int namecode = Integer.parseInt(dogs[0].getNameCode());
//
//		
//		} catch(NumberFormatException ne) { 
//			//첫번째 에러. 문자열을 숫자로 변환시, 유효하지 않은 숫자 형식인경우 에러발생.
//			//String -> int 로 parse 불가.
//			System.out.println("강아지 nameCode를 정할때 숫자로 해주세요!!");
//		}
//		
//		try {
//			dogs[6] = new Dog(4);
//		}catch(ArrayIndexOutOfBoundsException abe) {
//			//두번째 에러. 배열의 범위를 벗어났을 경우 발생.
//			System.out.println("강아지를 담을 수 있는 범위가 아닙니다 !");
//			return;
//		} finally {
//			System.out.println("강아지 분양을 실패하였습니다..");
//		}
//		
////	0
//	}
//}
