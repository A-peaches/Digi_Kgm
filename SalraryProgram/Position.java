package SalraryProgram;

public class Position {
	private String position; //직원의 급여
	private int positionPay; //직급별 급여
	
	//constructor
	public Position(){
	}
	
	public Position(String position){ //객체 생성과 동시에 position set
		this.position = position;
		positionPayResult(position);
	}
	
	public String getPosition() { //position get
		return position;
	}
	
	public int getPositionPay() { //position get
		return positionPay;
	}
	
	public void setPosition(String position) { //직급별 급여 set
		this.position = position;
		positionPayResult(position);
	}
	
	public void positionPayResult(String position) { //직급별 급여할당
		switch(position) {
		case "주임":
			positionPay = 300000;
			break;
		case "계장":
			positionPay = 500000;
			break;
		case "대리":
			positionPay = 700000;
			break;
		case "과장":
			positionPay = 1000000;
			break;
		case "차장":
			positionPay = 1200000;
			break;
		case "부장":
			positionPay = 1500000;
			break;
		case "임원":
			positionPay = 5000000;
			break;
		}
	}
}
