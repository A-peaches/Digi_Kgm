package Main;

public class Test {
	public static void main(String args[]) {
		int a = 0;
		do {
			a++;
			if (a == 3 )continue;
			if (a == 5 )break;
			System.out.print(a);
		} while (a<10);
		
	}
}
