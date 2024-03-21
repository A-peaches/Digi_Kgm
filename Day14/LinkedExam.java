package Day14;

import java.util.Scanner;

public class LinkedExam {
	static LinkNode head;
	static LinkNode cur;
	static LinkNode newNode;
	static LinkNode del;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		head = cur = newNode = new LinkNode();
		newNode.setData(1);
		newNode.next = null;

		// 뒤에 갖다 붙이기
		newNode.next = new LinkNode();
		newNode.next.setData(2);
		newNode.next.next = null;

		newNode.next.next = new LinkNode();
		newNode.next.next.setData(3);
		newNode.next.next.next = null;

		newNode.next.next.next = new LinkNode();
		newNode.next.next.next.setData(4);
		newNode.next.next.next.next = null;

		cur = head;
		while (cur != null) {
			System.out.print(cur.getData() + "|");
			cur = cur.next;
		}

		System.out.println(" ");

//		// ****************************************
//		// 몇번째 삽입할거양?
//
//		System.out.println("몇 번째에 삽입할거야?");
//		// 난 세번째! 3 !
//		int order = sc.nextInt();
//
//		if (order == 1) {
//			System.out.println("뭐 삽입할꺼야?");
//			int num = sc.nextInt();
//
//			newNode = new LinkNode();
//			newNode.next = head;
//			head = newNode;
//			newNode.setData(num);
//
//		} else {
//			System.out.println("뭐 삽입할꺼야?");
//			int num = sc.nextInt();
//			// 세번째의 전 노드를 찾는다! 커서로 !
//			cur = head;
//			for (int i = 1; i < order - 1; i++) { // 1번돌떄, 커서의 다음꺼.(2번째꺼)
//													// 2번돌떄, 2번째꺼의 다음꺼. 3번째거.
//													// 즉, 3번째꺼 찾을때는 2번만돌아야함. n-1
//				cur = cur.next;
//			}
//			// 전노드를 커서로잡고있기 !!
//			// 뉴노드 생성!!
//			newNode = new LinkNode();
//			// 뉴노드의 넥스트에 커서의 넥스트의 넥스트 넣기 !
//			newNode.next = cur.next;
//			// 커서의 넥스트에 뉴노드 넣기 !
//			cur.next = newNode;
//			// 데이터 넣기!!
//			newNode.setData(num);
//			// 끄으읕~
//		}
//
//		cur = head;
//		while (cur != null) {
//			System.out.print(cur.getData() + "|");
//			cur = cur.next;
//		}
		// ****************************************

		// **********************************
		// 중간꺼삭제!!!
		System.out.println("몇 번째꺼 삭제할거야?");
		// 난 세번째! 3 !
		int order = sc.nextInt();

		if (order == 1 && head !=null) {

			head = cur = head.next;

		} else {
		// 세번째의 전 노드를 찾는다! 커서로 !
		cur = head;
		for (int i = 1; i < order - 1; i++) { //
												// 커서잡기
			cur = cur.next;
		}
		del = cur.next; // delete도 잡기~
		// cur의 주소에 next next주소와 연결.
		cur.next = cur.next.next;
		// del node는 버려! null을가리킴.
		del.next = null;
		
	}
		cur = head;
		while (cur != null) {
			System.out.print(cur.getData() + "|");
			cur = cur.next;
		}

}
}

//몇번째 추가할거야?
//입력 => 삽입