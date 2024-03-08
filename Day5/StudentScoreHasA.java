package Day5;
//성적 처리 클래스
//Data management Class
public class StudentScoreHasA {
	//이름 => Name
	//과목 => Subject
	//총점, 평균 : field
	//이름,국어,영어,수학,총점
	private Name name;
	private Subject kor;
	private Subject mat;
	private Subject eng;
	private float avg;
	private int total;

	
	//constructor
	public StudentScoreHasA() {
		this(new Name(), new Subject(),new Subject(),new Subject());
	}
	
	public StudentScoreHasA(Name name) {
		this(name, new Subject(),new Subject(),new Subject());
	}
	
	public StudentScoreHasA(Name name, Subject kor) {
		this(name, kor ,new Subject(),new Subject());
	}
	
	public StudentScoreHasA(Name name, Subject kor, Subject mat) {
		this(name, kor ,mat,new Subject());
	}
	
	public StudentScoreHasA(Name name, Subject kor,Subject mat,Subject eng) {
		this.name = name;
		this.kor = kor; 
		this.mat = mat; 
		this.eng = eng; 
	}
	
	
	//getter setter 
	public String getName() {
		return this.name.getName();
	}

	public void setName(String name) {
		this.name.setName(name);
	}

	public int getKor() {
		return this.kor.getScore();
	}

	public void setKor(int kor) {
		this.kor.setScore(kor);
	}

	public int getMat() {
		return this.mat.getScore();
	}
	
	public void setMat(int mat) {
		this.mat.setScore(mat);
	}

	public int getEng() {
		return this.eng.getScore();
	}

	public void setEng(int eng) {
		this.eng.setScore(eng);
	}

	
	
	//method
	public int getTotal() {
		total = total();
		return total;
	}
	
	public float getAvg() {
		avg = avg();
		return avg;
	}

	


//	public void setTotal(int total) {
//		this.total = total;
//	}
//
//	public void setAvg(int avg) {
//		this.avg = avg;
//	}
	

	public int total() {
		total = this.kor.getScore()+
				this.eng.getScore()+
				this.mat.getScore();
		
		return total;
	}

	public float avg() {
		avg = getTotal()/3.f;
		
		return avg;
		
	}

//	public static void main(String[] args) {
//		
//		StudentScoreHasA stu = new StudentScoreHasA();
//		stu.setName("어피치");
//		stu.setKor(90);
//		stu.setMat(100);
//		stu.setEng(70);
//		
//		System.out.println(stu.getName());
//		System.out.println(stu.getKor());
//		System.out.println(stu.getMat());
//		System.out.println(stu.getEng());
//		System.out.println(stu.getTotal());
//		System.out.println(stu.getAvg());
//	}
}
