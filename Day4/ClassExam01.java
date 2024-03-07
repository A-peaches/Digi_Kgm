package Day4;

public class ClassExam01 {
	
	private int a;
	private char s;
	private float c;
	

	
	public static void main(String[] args) {
		
		ClassExam02 ce2 = new ClassExam02(100);
		
		ce2.setA(500);
		
		System.out.println(ce2.getA());
		
		
//	 ce2.a = 500;
//	System.out.println(ce2.a);
		
//		ClassExam01 ce = new ClassExam01();
//		 
//		ce.output(1,2);
//		ce.output(3.4);
//		ce.output(11);
//		ce.output();
//		
	}
	
	public void output(double a) {
		System.out.println(a);
	}
	
	public void output(int a, int b) {
		System.out.println(a+","+b);
	}
	
	public void output(int a) {
		System.out.println(a);
	}
	
	public void output() {
		System.out.println("Nothing");
	}

}
