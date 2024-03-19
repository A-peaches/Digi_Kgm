package BaseballGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program2 {

	private int count;
	private int accn;
	private int game;
	private int ball;
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

	public void getAverage() {
		System.out.println("총 평균 횟수는 " + accn / (float) game + "입니다 ! ");
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

	public void ball() {
		for (int i = 0; i < 3; i++) {
			if (pcContains(getUserIndex(i))) {
				ball++;
			}
			;
		}
	}

	public void strike() {
		for (int i = 0; i < 3; i++) {
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
		accn += count;
		reset();
	}
	public void match() {

		do {
			userAnswer();
			count++;

			// ball먼저 ...
			if (getPc().equals(getUser())) {
				out();
				break; 
				
			} else {
				// ball, strike 검증
				ball();
				strike();
			}

			System.out.println(strike + " strike" + "  " + ball + "  ball");
			userClear();
			ball = 0;
			strike = 0;

		} while (true);
	}

}
