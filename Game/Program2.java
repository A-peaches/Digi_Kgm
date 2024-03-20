package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {

	private int count; //대답횟수
	private int accn; //누적대답횟수
	private int game; //게임수
	private int ball; 
	public int getBall() {
		return ball;
	}

	public void setBall(int ball) {
		this.ball = ball;
	}

	public int getStrike() {
		return strike;
	}

	public void setStrike(int strike) {
		this.strike = strike;
	}

	private int strike;
	Pc pc;
	User user;
	Scanner sc;

	public Program2() {
		this.count = 0;
		this.accn = 0;
		this.game = 0;
		this.ball = 0;
		this.strike = 0;
		pc = new Pc();
		user = new User();
		sc = new Scanner(System.in);
	}

	public int question() {
		System.out.println("=========야구 숫자맞추기 게임========="+'\n' +
						   "게임 규칙 : 중복된 숫자는 허용하지 않습니다." +'\n' +
						   "=================================");
		System.out.println("게임을 몇번 하시겠습니까?");
		this.game = sc.nextInt();
		return game;
	}

	public void getAverage() { //누적합에 게임수를 나눠 평균출력
		System.out.println("총 평균 횟수는 " + accn / (float) game + "입니다 ! ");
		accn = 0;
		game = 0;
	}

	public String getPc() {
		return pc.correctGet();
	}

	public String getUser() {
		return user.answerGet();
	}

	public void setPc() {
		pc.correct();
	}

	public void pcClear() {
		pc.pcClear();
	}
	
	public void userClear() {
		user.userClear();
	}
	
	public int getUserIndex(int i) {
		return user.getUserIndex(i);
	}
	
	public int getPcIndex(int i) {
		return pc.getPcIndex(i);
	}
	
	public void reset() {
		pcClear();
		userClear();
		count = 0;
		ball = 0;
		strike = 0;
	}
	
	public boolean pcContains(int i) {
		return pc.pcContains(i);
	}

	public void ballStrike() { // ball먼저 검증. 숫자가 포함되어있다면 ball에 ++
		for (int i = 0; i < 3; i++) {
			if (pcContains(getUserIndex(i))) {
				ball++;
			}

		}

		for (int i = 0; i < 3; i++) { // strike해당시 ball은 차감.
			if (getPcIndex(i) == getUserIndex(i)) {
				strike++;
				ball--;
			}
		}
	}
	
	public void userAnswer() {
		user.userAnswer();
	}

	public void out() {
		System.out.println("정답입니다 !!! ");
		System.out.println(count + "번만에 정답을 맞추셨습니다!!");
		accn += count; //accn은 누적 count.
		reset(); // pc꺼, 사용자꺼, 대답한횟수, 볼스트라이크횟수 초기화.
	}
	public void match() {

		do {
			userAnswer(); // user가 스캐너를 통해 숫자3개입력 
			count++; // 사용자 숫자입력횟수 증가

			// ball먼저 ...
			if (getPc().equals(getUser())) { //pc의숫자와 user의숫자가 일치하다면
				out(); //정답시 호출
				break; 
				
			} else {
				// ball, strike 검증
				ballStrike();
			}

			System.out.println(getStrike() + " strike" + "  " + getBall()
			+ "  ball");
			userClear(); //strike와 ball을 출력 후 
			ball = 0; //user의 숫자와, ball과, strike 초기화.
			strike = 0;

		} while (true); //정답 맞출떅가지 반복~
	}
	
	public void gameStart() {
		while (true) {
			int game = question(); 
			//사용자에게 게임을 몇번할껀지 물어본다 
			int num = 0;

			do {
				setPc(); // pc가 랜덤숫자만들기 
//				System.out.println(getPc());
				match(); // 맞출떄까지 pc vs user
				
				num++;
			} while (game != num);
			//num을 1씩증가시켜주면서 사용자가 원하는 횟수만큼 게임 진행

			getAverage(); // 누적합/게임수
			
			System.out.println("계속 하시겠습니까? <1-yes / 2-no>");
			if (sc.nextInt() == 2) { //게임 계속 할지여부 질문~
				return;
			}
		}
	}

}
