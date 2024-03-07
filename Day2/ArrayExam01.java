package Day2;

import java.util.Scanner;
/*
* 인원수대로 성적 처리 프로그램. 
* 입력 : 이름 국,영,수 
* 연산 : 총점, 평균 출력 :
*  이름,국,영,수,총,평
*/
public class ArrayExam01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayExam01 arr = new ArrayExam01();
		
		//input 함수 호출 후 인원수 값 할당
		int cnt = arr.input_headcnt(sc);
		
		//배열 객체 생성 
		String []name = new String[cnt];
		int [][]score = new int[cnt][4]; 
		float []avg = new float[cnt];
		String []scoName = {"Name","Kor","Eng","Mat","Total","Abg"};
		
		//매개변수로 스캐너 객체와, 인원수, 이름, 점수 배열을 전달.
		//학생 이름과 점수 각각 인원수별 입력.
		arr.input_value(sc, cnt, name, score,scoName);
		
		//합계 함수 호출
		arr.sum_score(cnt, score);
		//평균 함수 호출
		arr.avg_score(cnt, score, avg);
		//결과 출력
		arr.result(cnt, name, score, avg);

	}
	
	
	private void result(int cnt, String[] name, int[][] score, float[] avg) {
		System.out.println("================= 성적 점수 판 ===================");
		System.out.println("이름"+"\t"+"국어"+"\t"+"영어"+"\t"+"수학"+"\t"+"총점"+"\t"+"평균");
		
		for(int i=0; i<cnt; i++) { // 3사람이면.. 0,1,2
				System.out.print(name[i]+"\t");
			for(int j=0; j<4; j++) { //4과목이기때문에, 0,1,2,3..
					System.out.print(score[i][j]+"\t"); // [0][0], [0][1], ....
				} 
					System.out.printf("%.2f\n",avg[i]);// 3명이면, 3명꺼출력. 0,1,2
				}
				
			}
	


	public int input_headcnt(Scanner sc) {
		System.out.println("시험을 응시한 학생 수를 입력해주세요 : ");
		int cnt = sc.nextInt();
		return cnt;
	}
	
	
	// 학생 이름과 성적 배열을 매개변수로 받아,
	// 데이터를 입력하는 함수 구현 
	public void input_value(Scanner sc, int cnt,String []name,int [][]score,String []scoName) {
		
		System.out.println("이름과 국어,영어,수학 순서대로 입력해주세요.");

		for(int i=0; i<cnt; i++) { //2명이면, 0,1
			System.out.println(scoName[0]+" Name : ");
			name[i] = sc.next(); 
			
			for(int j=0; j<3 ; j++) { //과목수는 3개. 0,1,2 실행.
			System.out.println(scoName[i+1]+" Score : ");
				score[i][j] = sc.nextInt();
			}
			
		System.out.println("성적 입력이 완료되었습니다.");
		}
	}
	
	//학생 성적 총합을 구하는 함수
	public void sum_score(int cnt,int [][]score) {
		for(int i=0; i<cnt; i++) { 
			for(int j=0; j<3; j++) {
				score[i][3] += score[i][j]; // [0][0]/[0][1]/[0][2] 전부 합산해서 [0][3]에 쏘옥~
			}
		}
	}
	
	//학생 성적 평균을 구하는 함수
	
	public void avg_score(int cnt,int [][]score,float []avg) {
		for(int i=0; i<cnt; i++) {
			avg[i] = score[i][3] / 3.0f;  
		}
	}
//			}
//			avg[i] = score[i][3]/3f;
//		}
//		
//		//avg = score[i][3]/3;
////		for(int i=0; i<score.length; i++) {
////
////		}



}
///*
//* 한사람의 성적 처리 프로그램. 
//* 입력 : 이름 국,영,수 
//* 연산 : 총점, 평균 출력 :
//*  이름,국,영,수,총,평
//*/
//
//String name;
//int score[] = new int[4]; // 국, 영, 수, 총
//float avg;
//int sub_range = score.length - 1; // 과목 개수(총점제외)
//

//System.out.print("이름을 입력해주세요 : ");
//name = sc.next(); // 사용자의 이름을 입력받음.
//
//System.out.println("차례대로 국어, 영어, 수학 점수를 입력해주세요  ");
//// 입력 및 총점
//for (int i = 0; i < sub_range; i++) {
//	System.out.print("Score : "+'\n');
//	score[i] = sc.nextInt();
//	
//	score[3] += score[i];
//}
//
//
//avg = (float)score[sub_range]/3;
//
//System.out.println(name);
//for (int sco : score) {
//	System.out.print(sco+"\t");
//}
//System.out.println(avg);
//	public String input_str() {
//		
//	}

//
//
//int[] arr = new int[5];
//
//for (int i : arr) {
//	System.out.println(i);
//
//	for(int i = 0; i<arr.length; i++) {
//	System.out.println(arr[i]);
//}
//