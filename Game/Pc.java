package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Pc { //PC꺼 
	private List<Integer> correct;
	Random rd;
	
	public Pc() { //생성자
		rd = new Random();
		correct =new ArrayList<Integer>();
	}
	
	public void correct() { //랜덤 숫자뽑기
		while (correct.size() < 3) { //3개가 될때까지
			int rand = rd.nextInt(10); //0부터9까지
			if (!correct.contains(rand)) { 
				//random으로 생성한 숫자가  correct에 이미 있으면, continue~ 
				//없으면 , add. size가 3이될때까지.
				correct.add(rand); //뽑은 숫자 correct에 넣어주기 
			} else {
				continue;
			}
		} 
	}
	public boolean pcContains(int i) {
		return correct.contains(i);
	}
	
	public int getPcIndex(int i) {
		return correct.get(i);
	}
	
	public void pcClear() {
		correct.clear();
	}

	public String correctGet() { // 컴퓨터가 생성한 숫자를 String으로 전환
		String result = correct.toString(); 
		result = result.substring(1, result.length() - 1).replace(", ", "");
		return result;
	}



	
}
