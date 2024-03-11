package SalraryProgram;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {
	private String employeeNum; //사번
	private String name; //직원이름
	private int temp = 0; // 0이면 정규직 1이면 비정규직
	private Position position; //직급
	private String hireDate; //입사일자
	private int tenure; //근속연수
	private Kpi kpi; //성과지표
	private OverPay overPay; // 시간외근무(해당시) 분입력 (SET)
	private PayStep payStep; // 자동계산
	private double salary;
	private double taxRate = 0.15;
	private double tax;
	
	//constructor
	//나머지는 자동계산, 필수입력 사번,  이름, 직급, 입사일자, 성과지표
	public Employee() {
	}
	
	public Employee(String employeeNum, String name, String position,
			String hireDate, String kpi, int overtime) {
		this.employeeNum = employeeNum;
		this.name = name;
		this.position = new Position(position);
		this.hireDate = hireDate;
		this.kpi = new Kpi(kpi);
		EmployeeTemp(this.position);
		tenure = tenureResult(hireDate);
		payStep = new PayStep(position,tenure);
		overPay = new OverPay(overtime);
		}
	
	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTemp() {
		return temp;
	}

	public String getPosition() {
		return position.getPosition();
	}

	public void setPosition(String position) {
		this.position.setPosition(position);
	}
	
	public int getPositionPay() {
		return position.getPositionPay();
	}
	
	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public int getTenure() {
		return tenure;
	}
	
	public String getKpi() {
		return kpi.getKpi();
	}

	public void setKpi(String kpi) {
		this.kpi.setKpi(kpi);
	}

	public int getOverPay() {
		return overPay.getOverPay();
	}
	
	public int getOverTime() {
		return overPay.getOverTime();
	}

	public void setOverTime(int overTime) {
		overPay.setOverTime(overTime);
	}

	public int getPayStep() {
		return payStep.getPayStep();
	}
	
	public int getStepSalary() {
		return payStep.getStepSalary();
	}
	
	public double getKpiRate() { //kpiRate get
		return kpi.getKpiRate();
	}
	
	public double getSalary() {
		return salary;
	}
	
	public double getTaxRate() {
		return taxRate;
	}
	
	public double getTax() {
		return tax;
	}

	public void EmployeeTemp(Position position) { //position 입력시 temp자동입력
		if (position.getPosition().equals("주임") || 
			position.getPosition().equals("임원")) {
			temp = 1;
		}
	}
		
	public int tenureResult(String hireDateStr) { //tenure 자동계산
		LocalDate hireDate = LocalDate.parse(hireDateStr, 
				DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate currentDate = LocalDate.now();
		
		Period result = Period.between(hireDate, currentDate);
		
		return result.getYears();
	}
	
	public void salaryResult() { // 급여반환
		salary = position.getPositionPay() + payStep.getStepSalary() +
				overPay.getOverPay() +
				(kpi.getKpiRate()*position.getPositionPay());
		tax = salary * taxRate;
		salary = salary - tax;
	}
	
	//호봉급 2,100,000
	//직급별급여 500,000
	//성과금 1,000,000
	//시간외수당 30,000
	//3,630,000 * 15% = 544,500원
	//3,630,000 - 3,085,500원
	
}
