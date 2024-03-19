package Game;

import java.util.Random;
import java.util.Scanner;

public class BabyJin {
	/*
	 * 개인과제 0~9 사이의 숫자 카드에서 임의의 카드 6장을 뽑았을 때, 3장의 카드가 연속적인 번호를 갖는 경우를 run으로 정의하고,
	 * 3장의 카드가 동일한 번호를 갖는 경우를 triplete로 정의한다 또한, 6장의 카드가 run과 triplete로만 구성된 경우를
	 * baby-gin으로 부른다 667767은 두 개의 triplete이므로 baby-gin이다 (666, 777) 054060은 한 개의
	 * run과 한 개의 triplete이므로 역시 baby-gin이다 (456, 000) 101123은 한 개의 triplete가 존재하나,
	 * 023이 run이 아니므로 baby-gin 이 아니다 (123을 run으로 사용하더라도 011이 run이나 triplete가 아님)
	 */
	int score[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	String mycard;
	private int run;
	private int triplete;
	private int babyGin;

	public BabyJin() {
		mycard = "";
		run = 0;
		triplete = 0;
		babyGin = 0;
	}

	public void suffle() {
		for (int i = 0; i < mycard.length(); i++) {
			int idx = mycard.charAt(i) - '0';
			score[idx] += 1;//
		}
	}

	public void myScore() {
		for (int j = 0; j < score.length - 2; j++) {
			if (score[j] >= 1 & score[j + 1] >= 1 & score[j + 2] >= 1) {
				run++;
				score[j] -= 1;
				score[j + 1] -= 1;
				score[j + 2] -= 1;
			}
		}

		for (int k = 0; k < score.length; k++) {
			if (score[k] >= 3) {
				triplete++;
				score[k] = 0;
			}
		}

		System.out.println("run : " + run + " / triplete : " + triplete);

		if (run == 1 & triplete == 1) {
			System.out.println("baby-gin 입니당~!!");
		}

		reset();
	}

	public void reset() {
		run = 0;
		triplete = 0;

		for (int i = 0; i < score.length; i++) {
			score[i] = 0;
		}
	}

	public void scorePrint() {
		for (int i : score) {
			System.out.print(i);
		}
		System.out.println("");
	}

	public void drawCard() throws InterruptedException {
		System.out.println("카드를 신나게 섞는중 ~");
		Thread.sleep(3000);
		Random rd = new Random();
		int draw = rd.nextInt(1000000);
		mycard = String.format("%06d", draw);
		System.out.println("your card :" + mycard);
	}

	public void gameStart() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			drawCard();
			suffle();
			myScore();

			System.out.println("계속 하시겠습니까? <1-yes / 2-no>");
			if (sc.nextInt() == 2) {
				break;
			}

		}
	}

//		bj.mycard = "012555";
//		bj.suffle();
//		bj.myScore();
//		

}
