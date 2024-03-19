package Day12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Program {
	
	int count = 0;
	int accn = 0;
	int game = 0;
	int strike = 0;
	int ball = 0;
	Set<Integer> pc = new HashSet<Integer>();
	List<Integer> correct = new ArrayList<Integer>();
	List<Integer> answer = new ArrayList<Integer>();
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);
	
	public void correct() {
	while(pc.size()<3) {
		this.pc.add(rd.nextInt(10));
	} //컴퓨터가 중복되지 않은 값으로 correct에 숫자 3개 추가.
	
	for(Integer i : pc) {
		this.correct.add(i);
	}
	
	}
	
	public String correctGet() {
		String result = correct.toString();
		result = result.substring(1, result.length() - 1).replace(", ", "");
		
		return result;
	}
	
	public String answerGet() {
		String result = answer.toString();
		result = result.substring(1, result.length() - 1).replace(", ", "");
		
		return result;
	}
	
	
	public void userAnswer() {
		System.out.println("중복된 숫자는 허용되지 않습니다.");  
		System.out.println("숫자 3자리를 입력해주세요. : "); 
		for(int i=0; i<3; i++) {
			answer.add(sc.nextInt());
		}
	}
	
	
	public int question() {
		System.out.println("게임을 몇번 하시겠습니까?");
		this.game = sc.nextInt();
		return game;
	}
	
	public void getAverage() {
		System.out.println("총 평균은 "+ accn / (float)game +"입니다 ! "); 
	}
	public void match() {
		
		do {
			userAnswer();
			count++;
			
		//ball먼저 ...
		if(correctGet().equals(answerGet())) {
			System.out.println("정답입니다 !!! ");
			System.out.println(count + "번만에 정답을 맞추셨습니다!!");
			correct.clear();
			answer.clear();
			pc.clear();
			accn += count;
			count =0;
			ball = 0;
			strike = 0;
			break;
			
		} else {
			//strike 검증 
			for(int i=0; i<3; i++) {
				if(correct.contains(answer.get(i))) {
					ball++;
				};
			}
			
			for(int i=0; i<3; i++) {
				if(correct.get(i) == answer.get(i)) {
					strike++;
					ball--;
				}
			}
			System.out.println(strike +" strike"+"  "+ ball+"  ball");
			answer.clear();
			ball = 0;
			strike = 0;
		}
		
		}while(true);
	}



}
