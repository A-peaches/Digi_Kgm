package Day9;

class ATMTwo implements Runnable {

	private long depositeMoney = 10000;

	@Override
	public void run() {
		 synchronized(this){
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				notify();
				wait();
				notify();
				
//				if (i == 1 | i > 2 & i % 2 == 0) {
//					wait();
//				}
			} catch(InterruptedException e) {
			
			}
			if (getDepositeMoney() <= 0) {
				break;
			}
			
			withDraw(1000);

		}
	}
}

 
	public void withDraw(long howMuch) {
		if (getDepositeMoney() > 0) {
			depositeMoney -= howMuch;
			System.out.print(Thread.currentThread().getName() + " , ");
			System.out.printf("%d %n", getDepositeMoney());
		} else {
			System.out.print(Thread.currentThread().getName() + " , ");
			System.out.println("End.");
		}
	}

	public long getDepositeMoney() {
		return depositeMoney;
	}
}

public class WaitNotifyEx {
	public static void main(String[] args) {
		ATMTwo atm = new ATMTwo();
		Thread mother = new Thread(atm, "mother");
		Thread son = new Thread(atm, "son");
		mother.start();
		son.start();
	}
}