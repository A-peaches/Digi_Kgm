package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortNum {
	/*
	 * 정돈된 수란 왼쪽부터 차례로 커지는 수라고 정의한다 (123, 235, 1359) 100~9,999,999 범위를 갖는 변수 X와,
	 * X~9,999,999 범위를 갖는 변수 Y를 입력받아 X와 Y 사이의 모든 정돈된 수와 전체 갯수를 출력하는 프로그램을 작성하라 X에
	 * 100, Y에 150 을 입력할 경우 출력 결과는 다음과 같다
	 */

	private List<Integer> myList;
	private List<Integer> endList;
	private int num;
	private int startNum;
	private int endNum;

	Scanner sc;

	public SortNum() {
		myList = new ArrayList<Integer>();
		endList = new ArrayList<Integer>();
		sc = new Scanner(System.in);
	}

	public boolean compare(int a, String temp) {
		boolean result = false;

		for (int j = 0; j < temp.length() - 1; j++) {
			if (temp.charAt(j) < temp.charAt(j + 1)) {
				result = true;
			} else {
				result = false;
				break;
			}
		}

		return result;
	}

	public void sortNum() {
		for (int i = getStartNum(); i < getEndNum(); i++) {
			String temp = String.valueOf(i);

			if (compare(temp.length(), temp)) {
				System.out.println(temp);
				addNum();
			}
		}
	}

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void addNum() {
		this.num++;
	}

	public void printNum() {
		System.out.println("총 슷자는 " + num + "개 입니당~!!");
	}

	public void inputNum() {
		System.out.println("Input X number : ");
		this.startNum = sc.nextInt();
		System.out.println("Input Y number : ");
		this.endNum = sc.nextInt();
	}

	public void gameStart() {
		while (true) {
			System.out.println("===========정돈된 수 구하기 게임=========");
			inputNum();
			sortNum();
			printNum();

			System.out.println("계속 하시겠습니까? <1-yes / 2-no>");
			if (sc.nextInt() == 2) {
				break;
			}
		}
	}



}
