package BaseballGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BaseballGame {

	public static void main(String[] args) {
		Program2 p = new Program2();
		int game = p.question();
		int num = 0;
		
		do {
			p.setPc(); // pc가 랜덤숫자만들기
			p.match(); // 무한루프 ~
			num++;
		} while (game != num);
		
		p.getAverage();
	}
}
