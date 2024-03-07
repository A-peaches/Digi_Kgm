package Day4;

public class ThisExam {

	int a;
	
	public int getA() {
		return a;
	}

	public void setA(int a) { // 얘는사실 객체주소.int a
		this.a = a;  //매개변수 = 지역변수가 내꺼! this로 객체의 주소를알려줘야 그 필드에 a값을 할당.
				//원래 내꺼가 우선이야! 
	}
	
	

	public static void main(String[] args) {
		ThisExam th = new ThisExam();
		
		th.setA(100); //th.100이 전송~
		System.out.println(th.getA());
	}
}
