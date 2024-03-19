package Day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BaseballGame {
	
	public static void main(String[] args) {
		Program p = new Program();
		int game = p.question();
		int num = 0;

		do {
			p.correct(); // pc가 랜덤숫자만들기 //pc꺼 프린트~
			System.out.println(p.correctGet());// 사용자꺼입력받기
			p.match();
			num++;
		} while (game != num);
		
		p.getAverage();
	}
}
