//package Day5;
//
//
//class A {  // Data class
//	private int a;
//
//	public A() {
//		System.out.println("A객체가 생성됨");
//	}
//
//	public void setA(int a) {
//		this.a = a;
//	}
//
//	public int getA() {
//		return a;
//	}
//}
//
//public class HasA { //Data management class
//	
//	private String name;
//	private A age;
//	
//	public HasA() {
//		System.out.println("Has객체가 생성됨");
//		name ="";
//		age = new A();
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public void setAge(int age) {
//		this.age.setA(age); //this.age.a = age; 불가
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public int getAge() {
//		return this.age.getA();
//	}
//	
//	public static void main(String[] args) {
//		
//		HasA has = new HasA();
//		has.setName("Pipi");
//		has.setAge(4);
//		
//		System.out.println("이름은 "+has.getName()+", 나이는 "+
//		has.getAge()+"살 입니다.");
//		
//		
//	}
//}
