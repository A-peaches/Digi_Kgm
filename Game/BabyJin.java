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
	//어떤 카드를 뽑았는지 확인하기 위한 배열
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

	public void suffle() { //뽑은 카드의 숫자에 해당하는 score인덱스 증가.
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
			} //연속으로 카드를 뽑은경우 run에해당! run점수를먹엇으니 점수 -1
		}

		int i = 0;
		while (i < score.length) {
			if (score[i] >= 3) {
				triplete++;
				score[i] -= 3;
				continue;
			}
			i++;
		} // 한 카드가 3장이상이면 triplete! tiplete -3. 

		System.out.println("run : " + run + " / triplete : " + triplete);

		if (run == 1 & triplete == 1) { //둘다해당되면 baby-gin!
			System.out.println("baby-gin 입니당~!!");
		}

		reset(); //게임이 끝낫으니 run,triplete,score전부 초기화.
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
		Thread.sleep(3000); //카드 뽑느라 3초 대기 ㅎ 
		Random rd = new Random();
		int draw = rd.nextInt(1000000); // 000000부터 ~ 999999중에 랜덤!
		mycard = String.format("%06d", draw); // 숫자를 스트링으로 변환.
		System.out.println("your card :" + mycard); //뽑은 카드 출력.
	}

	public void gameStart() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			drawCard(); //카드 6개랜덤으로 뽑기~!
			suffle(); //score 매기기
			myScore(); // triplete과 run 구한후 출력.

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
