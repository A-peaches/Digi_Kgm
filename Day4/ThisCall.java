package Day4;
//내 자신의 생성자를 생성자에서 호출하는 방법
//유지보수 ↑ , 왜냐하면. 만약 생성자에서 문제가생겼다면
// ThisCall(int a, int b)에 문제가생긴거니, 그것만 고치면 된다!!!
public class ThisCall {
	int a;
	int b;
	
	public ThisCall() {
//		this.a=0;
//		this.b=0;
		this(0,0);
		
	}
	
	public ThisCall(int a) {
//		this.a = a;
//		this.b = 0;
		this(a,0);
	}
	
	public ThisCall(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	
	public void setB(int b) {
		this.b = b;
	}
		
	public static void main(String[] args) {
	
		ThisCall th1 = new ThisCall();
		ThisCall th2 = new ThisCall(10);
		ThisCall th3 = new ThisCall(20,30);
		
		System.out.println(th1.getA()+","+th1.getB());
		System.out.println(th2.getA()+","+th2.getB());
		System.out.println(th3.getA()+","+th3.getB());
		
	}
	
		
}
