//package Day12_13;
//
//import java.util.Random;
//
//public class InsertSort {
//
//	private int count; // 비교 횟수
//
//	public InsertSort() {
//		count = 0;
//	}
//
//	public void insertSort(int[] data) {
//
//		// 배열의 길이만큼 반복
//		for(int i = 1; i< data.length; i++) {
//			int j; // i 인덱스를 기준으로 앞칸으로 한칸씩 비교하기위한j임!
//			int tmp = data[i]; // 첫번째 인덱스의 값을 tmp에 쓩슝~
//			
//			for (j = i; j>0 && data[j-1]>tmp; j--) {
//				count++;
//				//j는 i의값! 첫번째 돌때는 1, 두번째 돌때는 2 ...
//				//j인덱스가 0일때까지, 이전의 인덱스와 비교.(0과비교),j는 점점쭐어나간당.
//				data[j] = data[j-1]; // i꺼보다 j-1이 작으면 그위치로 숑!
//			}
//			data[j] = tmp;
//		}
//	}
//	public static void main(String[] args) {
//		InsertSort insertSort = new InsertSort();
//		int data[] = new int[50];
//
//		for (int i = 0; i < 50; i++) {
//			data[i] = (int)(Math.random()*1000);
//		}
//
//		insertSort.insertSort(data);
//
//		for (int i : data) {
//			System.out.print(i + ", ");
//		}
//		System.out.println();
//		System.out.println("비교 횟수: " + insertSort.count);
//	}
//}
