package threadex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameThread extends Thread {
	private int calcNumber;

	public NameThread(String name, int calcNumber) {
		super(name);
		this.calcNumber = calcNumber;
		log.info("new calcNumber - " + calcNumber);
	}

	public void run() {
		log.info("calcNumber - " + calcNumber);
		calcNumber++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("calcNumber - " + calcNumber);
	}

	public static void main(String[] args) throws InterruptedException {
		NameThread nameThread1 = new NameThread("calc 1", 1);
		nameThread1.start();
		Thread.sleep(500);
		NameThread nameThread2 = new NameThread("calc 2", 1);
		nameThread2.start();
		log.info("main Thread is ended.");
	}
}
