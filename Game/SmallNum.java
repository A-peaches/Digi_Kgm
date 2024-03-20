package Game;

import java.util.Scanner;

public class SmallNum {
	int num[]; // 임의 숫자 생성
	int temp; // 자리바꿔줄떄 임시저장소

	public SmallNum() {
		num = new int[4];
		temp = 0;
	}

	public void threeFor() { //포문 3번..
		for (int j = 0; j < 3; j++) {
			for (int i = 3; i > 0; i--) { // 3,2,1까지만
				// 1. 3 vs 2, 2 vs 1 , 3(승자랑) vs 1
				if (num[i] <= num[i - 1]) {
					temp = num[i]; // 얘가작은애
					num[i] = num[i - 1];
					num[i - 1] = temp;
				}
				; // 3번째꺼랑 2번째꺼랑 비교시 작은걸 앞으로.
			}
		}
	}

	public void zeroFor() {

		if (num[0] == 0) { // 0일때 자리변경
			for (int i = 1; i < 4; i++) {
				if (num[i] != 0) {
					num[0] = num[i];
					num[i] = 0;
					break;
				}
			}
		}

	}

	public void print() { //4자리 숫자 모두 출력
		for (int i : num) {
			System.out.print(i);
		}
		System.out.print('\t');
	}
	
	public void gameStart() {
		SmallNum sm = new SmallNum();
		Scanner sc = new Scanner(System.in);

		
		while (true) {
			System.out.println("시작값을 입력해주세요.");
			int start = sc.nextInt(); //시작값
			System.out.println("종료값을 입력해주세요.");
			int end = sc.nextInt(); //종료값
			
			System.out.println("Input" + '\t' + "Output");

			for (int i = start; i < end; i++) {
				num[0] = i / 1000; // i값 숫자나누기 
				num[1] = (i % 1000) / 100;
				num[2] = (i % 100) / 10;
				num[3] = i % 10;

				print(); //4자리 숫자 출력 (input)
				threeFor(); //작은수를 앞칸으로 한칸씩 땡겨가기 
				zeroFor(); //0이 없는 자리에 0배치.
				print(); //변경된 숫자 출력 (output)
				System.out.println("");
			}
			
			System.out.println("계속 하시겠습니까? <1-yes / 2-no>");
			if (sc.nextInt() == 2) { //게임 계속 할지여부 질문~
				break;
			}
		}
	}

}

