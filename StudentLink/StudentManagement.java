package StudentLink;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagement {
	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		AllStudent allstu = new AllStudent();
		int num = 0;
		int selectNum = 0;

		do {
			allstu.mainDisplay();
			num = sc.nextInt();

			switch (num) {
			case 1:
				allstu.addData();
				break;
			case 2:
				allstu.updateData();
				break;
			case 3:
				allstu.selectView();
				break;
			case 4:
				allstu.allView();
				break;
			case 5:
				allstu.deleteData();
				break;
			case 6:
				num = 6;
				break;
			default:
				System.out.println("1 ~ 6의 숫자로 입력해주세요.");
				break;
			}
		} while (num != 6);
		allstu.push();
		System.out.println("프로그램을 종료합니다.");

	}
}
