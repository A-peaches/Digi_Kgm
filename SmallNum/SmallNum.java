package SmallNum;

public class SmallNum {
	int num[]; // 임의 숫자 생성
	int zero;
	int temp;

	public SmallNum() {
		num = new int[4];
		zero = 0;
		temp = 0;
	}
	
	public void threeFor() {
		for (int i = 3; i > 0; i--) { // 3,2,1까지만
			// 1. 3 vs 2, 2 vs 1 , 3(승자랑) vs 1
			if (num[i] <= num[i - 1]) {
				temp = num[i]; // 얘가작은애
				num[i] = num[i - 1];
				num[i - 1] = temp;
			}
			; // 3번째꺼랑 2번째꺼랑 비교시 작은걸 앞으로.
		}
	}

	public void zeroFor() {
		
		if(num[0] == 0 && num[1] !=0 ) { //0이 하나일때
				temp = num[1];
				num[1] = 0;
				num[0] = temp;
			} else if(num[0]==0 && num[1]==0 && num[2]!=0) {
				num[0] = num[2];
				num[2] = 0;
			} else if(num[0]==0 && num[1]==0 && num[2]==0) {
				num[0] = num[3];
				num[3] = 0;
			}
		
	}

	
	public void print() {
		for(int i : num) {
			System.out.print(i);
		}
		System.out.print('\t');
	}

	public static void main(String[] args) {
		SmallNum sm = new SmallNum();
		System.out.println("Input"+'\t'+"Output");
		
		for (int i = 1000; i < 10000; i++) {
			sm.num[0] = i / 1000;
			sm.num[1] = (i % 1000) / 100;
			sm.num[2] = (i % 100) / 10;
			sm.num[3] = i % 10;
	

			sm.print();
			sm.threeFor();
			sm.threeFor();
			sm.threeFor();
			sm.zeroFor();
			sm.print();
			System.out.println("");
		}

	}
}

// switch문을통해 0의개수에 따른 정렬
// case1 if, 1개가 0이면 나머지 인덱스값들과 교환이 이루어진다.
// 이때 , 0은 1번째자리 고정.
// case2 if, 2개가 0이면 나머지 두개를 비교후 작은값은 1, 큰값은 3
// 이때, 0은 1,2번째자리고정.
// case3 if, 3개가 0이면 0이아닌 값을 0번 인덱스와 바꾼다.
// 이때, 0이아닌값과 첫번째 자리 체인지.
// case4 if, 1번째와 2번째비교해서 이긴애! a , 진애 a-1 3번째 4번째와 비교! 이긴애 b , 진애 b-1
// a vs b -> a승! b하고 a-1 비교! a-1승! b vs b-1이랑 비교 ! b가 승![a, a-1 , b , b-1]

//		for(int i =0; i <zeroIndex.length; i ++ ) {
//			System.out.println(zeroIndex[i]);
//		}
//		
