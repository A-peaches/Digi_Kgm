package Day8;

public class InterExam01  {
	
	private int a;
	
	public void  dispInter() {
		new AAA() { //interface or abstract class

			@Override
			public void disp() { //추상메서드 오버라이드
				System.out.println("여기는 AAA안의 disp다!");
				System.out.println(a+", "+A);
			}
			
		}.disp(); //객체생성 후 즉시 disp 함수 호출.
	}
	
	public static void main(String[] args) {
		InterExam01 inter = new InterExam01();
		inter.dispInter();
	}
}
//	public static void main(String[] args) {
////		InterExam01 inter = new InterExam01();
//		CCC inter = new InterExam01();
//		inter.disp();
//		inter.disp2();
////		inter.disp3();
//		inter.disp4();
//	}
//
//	@Override
//	public void disp() {
//		// TODO Auto-generated method stub
//		System.out.println("김근미 바부!!!!!!!!!!");
//		
//	}
//
//	@Override
//	public void disp2() {
//		// TODO Auto-generated method stub
//		System.out.println("disp2");
//		
//	}
//	
//	public void disp3() {
//		System.out.println("disp3");
//		
//	}
//
//	@Override
//	public void disp4() {
//		// TODO Auto-generated method stub
//		CCC.super.disp4();
//	}
//	
//	
//}
