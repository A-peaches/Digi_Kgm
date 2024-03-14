//package Day9;
//
//public class ThreadJoin implements Runnable{
//	
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		System.out.println("pipi  : pi~!! ");
//		first();
//	}
//	
//	public void first() {
//		System.out.println("cici : ci~!!");
//		second
//		();
//	}
//
//	public void second() {
//		System.out.println("popo : po~!!");
//	}
//	
//	
//	public static void main(String[] args) throws InterruptedException{
//		System.out.println(Thread.currentThread().getName() +" Start!");
//		Runnable tj = new ThreadJoin();
//		Thread tr = new Thread(tj);
//		tr.start();
////		try {
//			tr.join();
////		} catch(InterruptedException ie) {
////			ie.printStackTrace();
////		}
//		System.out.println(Thread.currentThread().getName() +" End!");
//	}
//}
