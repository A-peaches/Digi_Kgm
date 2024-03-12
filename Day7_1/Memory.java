package Day7_1;

public abstract class Memory { 
	//메서드 오버라이딩 강제성 부여한 추상클래스
	
	private int []arr = new int[5]; //5개의 데이터를 담을 수 있는 메모리공간
	private int size = 0; //데이터가 있는 사이즈는 0
	
	public Memory() {
		//Default 생성자
	}
	
	public void push(int a) {

		if (size <= 4) {
			arr[size] = a; // push할경우 데이터존재 size에 할당. 초깃값 0
			size++; // size 증가.
		} else {
			System.out.println("push 불가! 메모리가 꽉찼습니다! ");
		}
		;
	}
	
	public int getSize() {//배열에 담긴 데이터 사이즈를 반환.
		return size; 
	}  
	
	public void sizeRed() { //사이즈 감소 메서드 sizeReduction
		size--;
	}
	
	public void allView() { // 입력한 모든 데이터를 반환하는 메서드
		for(int i = 0; i < size ; i++) {
			System.out.print(arr[i] +"" 
		+'\t'+"|"+'\t');
		}
		System.out.println();
	}
	
	public void setArr(int index, int a) { //입력한 인덱스에 해당하는 메모리공간에 값을 set
		arr[index] = a;
	}
	
	public int getArr(int index) { //입력한 인덱스에 해당하는 값을 return
		return arr[index];	
	}
	
	public void myPrint(int a) { //인덱스 a에 해당하는 값 반환.
		System.out.println(arr[a]);
	}
	
	public abstract void pop();
	//구체화는 sub Class에서. 추상 메서드.
}
