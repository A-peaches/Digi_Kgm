package Day7_1;

import java.util.Scanner;

public class MemoryTest {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		MemoryOp op = new MemoryOp();
		int choice = 0;
		int select = 0;
		do {
			op.mainDisplay();
			choice = sc.nextInt();
			switch (choice) {

			case 1: // 메모리 DATA PUSH
				op.memorySelect();
				select = sc.nextInt();
				op.dynamic(select);
				op.input();
				break;
			case 2: // 메모리 DATE POP
				op.memorySelect();
				select = sc.nextInt();
				op.dynamic(select);
				op.pop();
				break;
			case 3: // 메모리 DATA VIEW
				op.memorySelect();
				select = sc.nextInt();
				op.dynamic(select);
				op.allView();
				break;
			case 4:
				break;
			}
		} while (choice != 4);

	}
}
