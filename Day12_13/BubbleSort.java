//package Day12_13;
//
//public class BubbleSort {
//	public static void main(String[] args) {
//		int data[] = { 90, 78, 100, 30, 55 };
//		bubbleSort(data);
//		for (int i : data) {
//			System.out.print(i + " ");
//		}
//	}
//	public static void bubbleSort(int[] data) {
//		int exchg = 0;
//		int forcount = 0;
//		for (int j = 0; j < data.length-1; j++) {
//			//  j < 4 배열의 크기가 5일때, 0,1,2,3만큼= (n-2) 반복. (두쌍씩 비교하기 때문.)
//			for (int i = data.length-1; i > j; i--) {
//			// j = 0  4,3,2 1 / j = 1 / 4, 3 ,2
//			// j = 2 / 4 3 / j =3 / 4  
//			// 반복될때마다 이미 끝의 숫자는 정렬이 완료되어있기때문에 , 반복을 하지 않음.
//				if (data[i - 1] > data[i]) {
//					int temp = data[i - 1];
//					data[i - 1] = data[i];
//					data[i] = temp; //교환
//					exchg++;//교환횟수
//				}
//				forcount++; //for문횟수
//			}
//		}
//		System.out.println("for :"  + forcount);
//		System.out.println("exchg :" +  exchg);
//	}
//}