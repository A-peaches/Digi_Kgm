package Day7;

class A {
//	public String toString() {
//		return super.toString() + "Superman";
//	}
//
//	public void disp() {
//		System.out.println("나는 A양!");
//	}
	public A() {
		//super(); 오브젝트
		System.out.println(" Super class ");
	}
}


public class IsAExam01 extends A{ //항상 Object를 상속받고있다. 생략가능.
	
	public IsAExam01() {
		//부모의 생성자를 호출
		//super(); 부모의 생성자를 호출하겠따.
		//생성자가 불러지는 첫번째 라인에 존재해야
		super(); 
		System.out.println(" Sub class ");
		
	}
//	  public String toString() {
//	        return super.toString()+"Superman";
//	    } // 근미언닝 ~ 따랑해용 ~ 하투하투 
//	      // 근미의 상속을 받은 설화+수희
	  	  // 제가 더 따랑해용 ~
	  
	public static void main(String args[]) {
		IsAExam01 isa = new IsAExam01();
		
//		System.out.println(isa);
//		System.out.println(isa.toString()); 
		//Object에 존재하는 toString.
		//=reference 변수명을 출력시 toString 생략
		

	}
}
