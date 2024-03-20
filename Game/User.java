package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
	private List<Integer> answer;
	Scanner sc;
	
	public User() {
		 sc = new Scanner(System.in);
		 answer = new ArrayList<Integer>();
	}

	public String answerGet() { // 사용자가 입력한 숫자를 String으로 전환
		String result = answer.toString();
		result = result.substring(1, result.length() - 1).replace(", ", "");
		return result;
	}
	
	public void userClear() {
		answer.clear();
	}
	
	public int getUserIndex(int i) {
		return answer.get(i);
	}
	
	public void userAnswer() { 
		System.out.println("숫자 3자리를 입력해주세요. : "); 
		
		while (answer.size() < 3) { // 3자리까지만 받음
			int num = sc.nextInt(10); // 숫자한개씩
			if (!answer.contains(num)) {  
				answer.add(num); //중복된값이 있으면 다시 입력필수
			} else {
				System.out.println("중복된 값은 허용되지않습니다. 다시 입력해주세요!");
				continue;
			}
		} 

	}
	
	
}
