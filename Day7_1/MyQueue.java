package Day7_1;

public class MyQueue extends Memory {
	
	public MyQueue() {
		//디폴트 생성자
	}
	
	@Override
	public void pop() {//First in First out. (FIFO)
		if (super.getSize() > 0) {
		super.myPrint(0); //무조건 가장 처음에 들어간 값 반환.
		queueReset();
		super.sizeRed();
		} else {
			System.out.println("pop 불가! 메모리에 데이터가 존재하지 않습니다.");
		}
	}
	
	public void queueReset() {
		
		for (int i=0 ; i < super.getSize()-1; i++){
			super.setArr(i,super.getArr(i+1)); 
			// 만약 size가 3이라면, 
			//0위치에 1을넣고, 1위치에 2를넣고, 끝~!
		}
	}

}
