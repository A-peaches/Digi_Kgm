package Day7_1;

import java.util.Scanner;

public class MemoryOp {
	private MyStack myStack;
	private MyQueue myQueue;
	Memory myMemory;
	Scanner sc = new Scanner(System.in);
	
	public MemoryOp() { //queue와 stack 메모리 자동 생성.
		myStack = new MyStack();
		myQueue = new MyQueue();
	} //디폴트 생성자
	
	
	public void mainDisplay() {
		System.out.println("원하는 기능을 선택해주세요." +'\n'+
						   "메모리당 1개 까지만 생성할 수 있습니다." +'\n' +
						   "1. 메모리 입력 2. 메모리 출력 3. 메모리 조회 4. 종료");
	}
	
	public void memorySelect() {
		System.out.println("========== 메모리 생성 ==========" +'\n'+
						  "어떤 메모리를 사용할까요?" +'\n'+
						  "1. stack  2. pop");
	}
		
	public void dynamic(int a) {
		if (a == 1) {
			myMemory = myStack;
		} else {
			myMemory = myQueue;
		}
	}
	


	public void allView() { //1선택시 stack 출력, 2선택시 queue 출력
		myMemory.allView();
//		if (select == 1) {
//			myStack.allView();
//		} else {
//			myQueue.allView();
//		}
		
	}
	
	public void input() {
		System.out.println("데이터를 입력해주세요.");
		myMemory.push(sc.nextInt());

		System.out.println("데이터 입력이 완료되었습니다.");
	}

	public void pop() {
		myMemory.pop();
		System.out.println("데이터 출력이 완료되었습니다.");
	}

}
