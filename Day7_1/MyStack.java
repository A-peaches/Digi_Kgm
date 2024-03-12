package Day7_1;

public class MyStack extends Memory {

	public MyStack() {
		// 디폴트 생성자.");
	}

	@Override
	public void pop() {// First in Last out (FILO)
		if (getSize() > 0) {
			super.myPrint(getSize() - 1); // 가장 마지막에 넣은 데이터 출력
			super.setArr(getSize() - 1, 0); // 빠져나간 데이터 리셋. 초기값 0 할당.

			super.sizeRed(); // size 1감소.
		} else {
			System.out.println("pop 불가! 메모리에 데이터가 존재하지 않습니다.");
		}
	}
}
