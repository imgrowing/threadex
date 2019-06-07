package threadex.thread;

import lombok.extern.slf4j.Slf4j;
import threadex.ThreadSample;

import java.util.HashMap;
import java.util.Hashtable;

@Slf4j
public class InfiniteThread extends Thread {

	@Override
	public void run() {
		while (true) {
			String str = "String";
			new HashMap();
			new Hashtable();

			if (this.isInterrupted()) {
				log.info("this.isInterrupted() : {}", this.isInterrupted());
				break;
			}
		}
	}

	public static void main(String[] args) {
		InfiniteThread thread = new InfiniteThread();
		thread.start();

		try {
			Thread.sleep(500);
			thread.interrupt();
			System.out.println("interrupt() called");
			thread.join();
		} catch (InterruptedException ie) {
			log.error(ie.toString());
		}

		log.info("isAlive = " + thread.isAlive());
		log.info("isInterrupted = {}", thread.isInterrupted());
		log.info("state = {}", thread.getState());
	}
}
