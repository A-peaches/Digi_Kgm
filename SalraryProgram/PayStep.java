package SalraryProgram;

public class PayStep {
	private int payStep; //호봉
	private int stepSalary; //호봉별급여
	private int tenure; //근속연수
	private String position; //직급
	
	
	//constuctor
	public PayStep() { //디폴트 생성자
	}
	
	//position/tenure를 입력받는 생성자
	public PayStep(String position, int tenure) { 
		this.position = position; 
		this.tenure = tenure;
		StepSalaryResult();
	}
	
	public void setTenure(int tenure) {
		this.tenure = tenure;
		StepSalaryResult();
	}
	
	public void setPoision(String position) {
		this.position = position;
		StepSalaryResult();
	}
	
	public void setStepSalary() {
		StepSalaryResult();
	}
	
	public int getPayStep() {
		return payStep;
	}
	
	public int getStepSalary() {
		return stepSalary;
	}
	
	public void StepSalaryResult() { //직급별, 호봉별 호봉급 계산 및 호봉계산.
		switch(position) {
		case "주임":
				stepSalary = 0;
				payStep = 0;
		case "임원":
				stepSalary = 0;
				payStep = 0;
			break;
		case "계장":
			if (tenure <= 3) {
				stepSalary = 1900000;
				payStep = 1;
			} else {
				stepSalary = 2100000;
				payStep = 2;
			}
			break;
		case "대리":
			if (tenure <= 3) {
				stepSalary = 2500000;
				payStep = 1;
			} else {
				stepSalary = 2700000;
				payStep = 2;
			}
			break;
		case "과장":
			if (tenure <= 3) {
				stepSalary = 3100000;
				payStep = 1;
			} else {
				stepSalary = 3300000;
				payStep = 2;
			}
			break;
		case "차장":
			if (tenure <= 3) {
				stepSalary = 3700000;
				payStep = 1;
			} else {
				stepSalary = 3900000;
				payStep = 2;
			}
			break;
		case "부장":
			if (tenure <= 3) {
				stepSalary = 4300000;
				payStep = 1;
			} else {
				stepSalary = 4500000;
				payStep = 2;
			}
			break;
		default :
			return;
		}
	}
	

	
	
}
