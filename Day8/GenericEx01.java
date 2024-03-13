//package Day8;
//
//public class GenericEx01<T> {
//	T[] v;
//	
//	public void set(T[] n) {
//		v = n;
//	}
//	
//	public void print() {
//		for (T s: v)
//			System.out.println(s);
//	}
//
//	public static void main(String[] args) {
//		GenericEx01<String> t = new GenericEx01<String>();
//		
//		String[] zz = {"어","피","치"};
//		t.set(zz);
//		t.print();
//		
//		GenericEx01<Integer> h = new GenericEx01<Integer>();
//		
//		Integer[] hh = {1,2,3};
//		h.set(hh);
//		h.print();
//	}
//	
//}