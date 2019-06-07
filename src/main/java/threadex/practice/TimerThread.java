package threadex.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TimerThread extends Thread {

	@Override
	public void run() {
		printCurrentTime();
	}

	private void printCurrentTime() {
		for (int i = 0; i < 10; i++) {
			log.info("{} : {}", new Date().toString(), System.currentTimeMillis() - System.currentTimeMillis() % 1000);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TimerThread thread = new TimerThread();
		thread.start();
	}
}
