package SalraryProgram;

public class OverPay {
	private int overTime; // 시간외근무(분)
	private int overPay; // 30분당 시간외수당

	// constructor
	public OverPay() {
	}
	
	public OverPay(int overTime) { //객체 생성 동시 overtime
		this.overTime = overTime;
		overPayResult(overTime);
	}

	// getter, setter
	public int getOverPay() { // 시간외수당 반환
		return overPay;
	}

	public int getOverTime() { // 사용자가 원한다면 시간외근무(분)을
								// 불러올 수 있음.
		return overTime;
	}


	public void setOverTime(int overTime) {
		this.overTime = overTime;
		overPayResult(overTime);	//overtime입력시 자동으로 overPay계산.
	}

	public void overPayResult(int overTime) {
		overPay =(overTime / 30) * 10000; // 30분 미만은 버림.int형
	}

}
