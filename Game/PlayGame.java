package Game;

import java.util.Scanner;

public class PlayGame {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		SortNum sn = new SortNum();
		BabyJin bj = new BabyJin();
		SmallNum sm = new SmallNum();
		Program2 bg = new Program2();
		
		while (true) {
			System.out.println("=============Mini game==============");
			System.out.println("1. 숫자 야구 게임          2. 작은 수 변경");
			System.out.println("3. 정돈된 수   4. baby-gin  5. 게임 종료");
			System.out.println("====================================");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				bg.gameStart(); //야구게임!
				break;
			case 2:
				sm.gameStart(); //작은 수 변경게임!
				break;
			case 3:
				sn.gameStart(); //정돈된 수 게임!
				break;
			case 4:
				bj.gameStart(); //베이비진 게임!
				break;
			case 5: // switch문  while문 전부 탈출 
				System.out.println("========== 게임을 종료합니다 ===========");
				return;
			}
		}
	}
}
