//package Day12_13;
//
//public class QueueExam {
//	int queue[] = new int[5];
//	int front = 0;
//	int near = 0;
//
//	public void enqueue(int a) {
//		try {
//			if (front != 0 & near == 5) {
//				shift(a);
//			} else {
//
//				queue[near] = a;
//				near += 1;
//			}
//		} catch (ArrayIndexOutOfBoundsException ae) {
//
//			System.out.println("큐가 꽉차서 넣을수가 없어용!");
//		}
//	}
//
//	public void shift(int a) {
//
//		int count = 0;
//		for (int i = front; i < 5; i++) {
//			queue[i - front] = queue[i];
//			count++;
//		}
//		near = count;
//		queue[count] = a;
//		near++;
//		front = 0;
////			System.out.println(near);
//
//		for (int j = near; j < 5; j++) {
//			queue[j] = 0;
//		}
//
//	}
//
//	public void dequeue() {
//		System.out.println("pop : " + queue[front]);
//		queue[front] = 0;
//		front++;
//
//		if (front == near) {
//			front = 0;
//			near = 0;
//		}
//	}
//
//	public void print() {
//		System.out.print("[ ");
//		for (int i : queue) {
//			System.out.print(i + ", ");
//		}
//		System.out.println("" + "]");
//	}
//
//	public static void main(String[] args) {
//		QueueExam qe = new QueueExam();
//		qe.enqueue(1);
//		qe.enqueue(2);
//		qe.enqueue(3);
//		qe.enqueue(4);
//		qe.enqueue(5);
//		qe.print();
//		qe.dequeue();
//		qe.dequeue();
//		qe.print();
//		qe.dequeue();
//		qe.print();
//		qe.enqueue(6);
//		qe.print();
//		qe.enqueue(7);
//		qe.print();
//	}
//}
