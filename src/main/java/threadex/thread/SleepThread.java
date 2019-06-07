package threadex.thread;

import lombok.extern.slf4j.Slf4j;
import threadex.ThreadSample;

@Slf4j
public class SleepThread extends Thread {
	long sleepTime;

	public SleepThread(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		try {
			log.info("Sleeping " + getName());
			Thread.sleep(sleepTime);
			log.info("Stopping " + getName());
		} catch (InterruptedException ie) {
			log.error("in run() method", ie);
		}
	}

	public static void checkThreadState1() {
		SleepThread thread = new SleepThread(2000);

		try {
			log.info("thread state=" + thread.getState()); // -> NEW
			thread.start();
			log.info("thread state(after start)=" + thread.getState()); // -> RUNNABLE

			Thread.sleep(1000);
			log.info("thread state(after 1sec)=" + thread.getState()); // -> TIMED_WAITING

			thread.join(); // thread의 run() 수행이 끝나기를 기다림
			log.info("thread state(after join)=" + thread.getState()); // -> TERMINATED
			thread.interrupt();
			log.info("thread state(after interrupt)=" + thread.getState()); // -> TERMINATED
		} catch (InterruptedException ie) {
			log.error("interrupted", ie);
		}
	}

	public static void checkThreadState2() {
		SleepThread thread = new SleepThread(2000);

		try {
			log.info("thread state=" + thread.getState()); // -> NEW
			thread.start();
			log.info("thread state(after start)=" + thread.getState()); // -> RUNNABLE

			Thread.sleep(1000);
			log.info("thread state(after 1sec)=" + thread.getState()); // -> TIMED_WAITING

			thread.join(500); // thread의 run() 수행이 끝나기를 기다림
			log.info("thread state(after join)=" + thread.getState()); // -> TIMED_WAITING
			thread.interrupt();
			log.info("thread state(after interrupt)=" + thread.getState()); // -> TERMINATED

			Thread.sleep(100);
			log.info("thread state(after interruptedException occurred)=" + thread.getState()); // -> TERMINATED
		} catch (InterruptedException ie) {
			log.error("main interrupted", ie);
		}
	}

	public static void main(String[] args) {
		SleepThread.checkThreadState1();
		SleepThread.checkThreadState2();
	}
}
