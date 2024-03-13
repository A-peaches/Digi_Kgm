package Day8;

public class InnerClassExam01 {
	
	private int a;
	private static int b;
	
	public void dispTest() {
		class Test {
			public void disp() {
				System.out.println(a);
				System.out.println(b);
			}
		}
		
		Test test = new Test();
		test.disp();
	}
	
	public static void main(String[] args) {
		
		InnerClassExam01 outer = new InnerClassExam01();
//		InnerClassExam01.Test inner = outer.new Test();
		
//		
//		InnerClassExam01.Test test = new InnerClassExam01().new Test();
//		inner.disp();
//		test.disp();
		outer.dispTest();
		
		
	}
}
