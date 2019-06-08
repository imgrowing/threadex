package threadex.stop_thread;

import java.util.concurrent.TimeUnit;

public class StopFlagEx {
	public static void main(String[] args) {
		PrintThread1 printThread = new PrintThread1();
		printThread.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}

		printThread.setStop(true);
	}
}
