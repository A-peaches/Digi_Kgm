package Day12_13;

public class BubbleSort {
	public static void main(String[] args) {
		int data[] = { 90, 78, 100, 30, 55 };
		bubbleSort(data);
		for (int i : data) {
			System.out.print(i + " ");
		}
	}
	public static void bubbleSort(int[] data) {
		int exchg = 0;
		for (int i = 0; i < data.length-1; i++) {
			for (int j = data.length-1; j > i; j--) {
				if (data[j - 1] > data[j]) {
					int temp = data[j - 1];
					data[j - 1] = data[j];
					data[j] = temp; //교환
					exchg++;//교환횟수
				}
			}
		}
	}
}