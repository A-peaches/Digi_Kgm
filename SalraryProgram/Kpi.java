package SalraryProgram;

public class Kpi {
	private String kpi; //성과:탁월,우수,양호,보통,미흡
	private double kpiRate; //성과점수에 따른 성과지급률
	
	//constructor
	public Kpi() {
	}
	
	public Kpi(String kpi) { // 객체 생성과 동시에 kpi set
		this.kpi = kpi;
		kpiRateResult(kpi);
	}
	
	
	public void setKpi(String kpi) { // kpi 데이터 입력시 자동으로  kpiRate계산
		this.kpi = kpi;
		kpiRateResult(kpi);
	}
	
	public double getKpiRate() { //kpiRate get
		return kpiRate;
	}
	
	public String getKpi() { //Kpi get
		return kpi;
	}
	
	
	public void kpiRateResult(String kpi) { //kpi별 rate 할당
		switch(kpi) {
		case "탁월" :
			kpiRate = 2;
			break;
		case "우수" :
			kpiRate = 1.5;
			break;
		case "양호" :
			kpiRate = 1;
			break;
		case "보통" :
			kpiRate = 0.5;
			break;
		case "미흡" :
			kpiRate = 0.25;
			break;
		}
	}
	
}
