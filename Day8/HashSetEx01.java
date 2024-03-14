//package Day8;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
//public class HashSetEx01 {
//	public static void main(String[] args) {
//		String[] str = {"Apple","Banana","Apple","Orange"};
//		int[] arr = {1,2,3,4,5};
//		
//		HashSet<String> hs1 = new HashSet<String>();
//		HashSet<String> hs2 = new HashSet<String>();
//		
//		for(String n : str) {
//			if(!hs1.add(n)) {
//				hs2.add(n);
//			}
//
//		}
//		
//		
//		System.out.println("h1 : "+hs1);
//		hs1.removeAll(hs2);
//		System.out.println("hs1 :"+hs1);
//		System.out.println("hs2 :"+hs2);
////		System.out.println("arr :"+Arrays.toString(arr));
//		System.out.println("arr :"+(arr));
//		//HashSet은 toSting이 오버라이드 되어있어서 직접출력이 가능하나,
//		//배열은 arr.toStirng()을 통해 출력해야함
//	}
//	
//}
