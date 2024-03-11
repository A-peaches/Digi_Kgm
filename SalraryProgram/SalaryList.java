package SalraryProgram;

import java.util.Scanner;

public class SalaryList {
	Employee []employeeList = new Employee[100];
	Employee emlployee;
	Scanner sc = new Scanner(System.in);
	int num = 0;
	//constructor 생략
	
	public void mainDisplay() {
		System.out.println("============ 직원 급여 관리 프로그램 ============" + '\n'+
						   "1. 직원 정보 입력" +'\n' +
						   "2. 직원 수정" + '\n' +
						   "3. 직원 검색" + '\n' +
						   "4. 직원 출력" + '\n' +
						   "5. 종료" + '\n' +
						   "6. 삭제" + '\n' +
						   "==========================================");
	}
	
	public void input() {
	   //나머지는 자동계산, 필수입력 사번,  이름, 직급, 입사일자, 성과지표
		//(int employeeNum, String name, String position,String hireDate, String kpi,overtime) 
		
		System.out.println("사번을 입력해주세요 :");
		String employeeNum = sc.next();
		System.out.println("직원의 이름을 입력해주세요 :");
		String name = sc.next();
		System.out.println("직급을 입력해주세요 :");
		String position = sc.next();
		System.out.println("입사일자를 입력해주세요. (ex 2017-01-01)");
		String hireDate= sc.next();
		System.out.println("성과를 입력해주세요.");
		String kpi = sc.next();
		System.out.println("시간외 근무(분)단위로 입력해주세요.");
		int overtime = sc.nextInt();
		employeeList[num] = new Employee(employeeNum, name, position, hireDate, kpi, overtime);
		num++;
		
	}
	
	public void viewInfo(int i) {
		System.out.println(employeeList[i].getEmployeeNum()+'\t'+
						   employeeList[i].getName() +'\t' +
						   employeeList[i].getTemp() +'\t' +
						   employeeList[i].getPosition() +'\t'+
						   employeeList[i].getHireDate() +'\t'+
						   employeeList[i].getTenure() +'\t'+
						   employeeList[i].getKpi() +'\t'+
						   employeeList[i].getOverTime());
	}
	
	public void viewPayInfo(int i) {
		employeeList[i].salaryResult(); // 급여 계산
		 // 사번      기본급     호봉급     성과금     시간외수당      세율      세전     세후
		System.out.println(employeeList[i].getEmployeeNum()+'\t'+
						   employeeList[i].getPositionPay() +'\t' +
						   employeeList[i].getStepSalary() +'\t' +
						   employeeList[i].getKpiRate()*employeeList[i].getPositionPay()+'\t'+
						   employeeList[i].getOverPay() +'\t'+
						   employeeList[i].getTaxRate() +'\t'+
						   (employeeList[i].getSalary()+employeeList[i].getTax()) +'\t' +
						   employeeList[i].getSalary() +'\t');
	}

	
	public void search() {

		System.out.println("1. 직원 인사 정보 조회  2. 직원 급여명세서 조회");
		int type = sc.nextInt();

		if (type == 1) {
			System.out.println("검색할 직원의 사번을 입력해주세요.");
			String employeeNum= sc.next(); // name에 입력값

			for (int i = 0; i < num; i++) {
				
				if (employeeList[i].getEmployeeNum().equals(employeeNum)) {
					System.out.println("=========================================================================" +'\n'+
					 		   "  사번    이름   비정규직    직급       입사일자      근속연수    성과점수   시간외근무(분)" + '\n');
					viewInfo(i);
				} else
					continue;

			}

		} else if (type == 2) {

			System.out.println("검색할 직원의 사번을 입력해주세요.");
			String employeeNum= sc.next();

			for (int i = 0; i < num; i++) {
				if (employeeList[i].getEmployeeNum().equals(employeeNum)) {
					System.out.println("=========================================================================================" +'\n'+
							 		   "  사번     기본급    호봉급    성과금          시간외수당    세율       세전              세후" + '\n');
					viewPayInfo(i);
				}
					continue;

			}
		}
	}

	public void allView() {
		System.out.println("=========================================================================" +'\n'+
		 		   "  사번    이름   비정규직    직급       입사일자      근속연수    성과점수   시간외근무(분)" + '\n');
		for (int i =0; i<num; i++) {
			viewInfo(i);
		}
	}
	
	
	public void update() {
		int updateNum = 0;
		String prevStr = "";
		int prevInt = 0;
		int close = 0;
		
		do {
			String employeeNum = "";
			System.out.println("정보를 수정할 직원 사번을 입력해주세요.");
			employeeNum = sc.next();

			for (int i = 0; i < num ; i++) {
					//"  사번    이름   비정규직    직급       입사일자      근속연수    성과점수   시간외근무(분)"
				if (employeeList[i].getEmployeeNum().equals(employeeNum)) {
					System.out.println("=========================================================================" +'\n'+
					 		   "  사번    이름   비정규직    직급       입사일자      근속연수    성과점수   시간외근무(분)" + '\n');
					viewInfo(i);
					
					while (updateNum != 4) {
						System.out.println("수정할 항목을 입력해주세요." + '\n' 
										  + "1. 직급" + '\t' + "2. 성과점수 " + '\n' 
										  + "3. 시간외근무" + '\t' + "4. 다른 학생 수정"+'\n');
						updateNum = sc.nextInt();
						switch (updateNum) {
						case 1:
							System.out.println("수정할 직급을 입력해주세요.");
							prevStr = employeeList[i].getPosition();
							employeeList[i].setPosition(sc.next());
							System.out.println( "수정이 완료되었습니다." + '\n' 
												+ "수정 전 : " + prevStr + '\t' 
												+ "수정 후 : " + employeeList[i].getPosition());
							return;
						case 2:
							System.out.println("수정할 성과점수를 입력해주세요.");
							prevStr = employeeList[i].getKpi();
							employeeList[i].setKpi(sc.next());
							System.out.println( "수정이 완료되었습니다." + '\n' 
									+ "수정 전 : " + prevStr + '\t' 
									+ "수정 후 : " + employeeList[i].getKpi());
							return;
						case 3:
							System.out.println("수정할 시간외근무(분)을 입력해주세요.");
							prevInt = employeeList[i].getOverTime();
							employeeList[i].setOverTime(sc.nextInt());
							System.out.println( "수정이 완료되었습니다." + '\n' 
									+ "수정 전 : " + prevInt + '\t' 
									+ "수정 후 : " + employeeList[i].getOverTime());
							return;
						case 4:
							break; // 	다른직원선택시 switch문을 벗어난다.
						default:
							System.out.println("1 ~ 4 사이의 숫자로 입력해주세요.");
						}
						
						if (updateNum == 4) {
							break; // switch문을 벗어난 뒤 for문을 벗어난다.
						}
					}
				} else
					continue;
			}
		} while (true);
	}
}
