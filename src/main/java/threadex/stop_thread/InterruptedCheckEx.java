package threadex.stop_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class InterruptedCheckEx {
	public static void main(String[] args) {
		Thread thread = new PrintThread3();
		thread.start();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
		}

		log.info("before interrupt");
		thread.interrupt();
		log.info("after interrupt");
	}
}
