//package Day9;
//
//public class SingleThreadEx implements Runnable{
//	private int[] temp;
//	
//	public SingleThreadEx() {
//		temp = new int[10];
//		for(int start = 0; start <temp.length; start++) {
//			temp[start] = start;
//		}
//	}
//	
//	@Override
//	public void run() {
//		for(int start : temp) {
//			try {
//				Thread.sleep(1000);
//			}catch(InterruptedException ie) {
//				ie.printStackTrace();
//			}
//			System.out.println("현재 쓰레드 이름 : " + Thread.currentThread().getName());
//			System.out.println("temp value : "+start);
//		}
//	}
//	
//	public static void main(String[] args) {
//		Runnable rn = new SingleThreadEx();
////		Thread th = new Thread(rn);
//		
//		Thread th = new Thread(rn);
//		th.setName("pipi");	
//	    th.start();
//
//	}
//}
