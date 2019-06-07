package threadex.thread;

import lombok.extern.slf4j.Slf4j;
import threadex.ThreadSample;

@Slf4j
public class StateThread extends Thread {

	private Object monitor;

	public StateThread(Object monitor) {
		this.monitor = monitor;
	}

	@Override
	public void run() {
		try {
			for (int loop = 0; loop < 10000; loop++) {
				String a = "A";
			}

			synchronized (monitor) {
				monitor.wait();
			}

			log.info(getName() + "is notified.");
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			log.error("run()", ie);
		}
	}

	public static void main(String[] args) {
		checkThreadState1();
		log.warn("=================================");
		//checkThreadState2();
		checkThreadState3();
	}

	private static void checkThreadState1() {
		Object  monitor = new Object();
		StateThread thread = new StateThread(monitor);

		try {
			log.info("thread state={}", thread.getState()); // NEW
			thread.start();
			log.info("thread state(after start)={}", thread.getState()); // RUNNABLE

			Thread.sleep(100);
			log.info("thread state(after 0.1 sec)={}", thread.getState()); // WAITING

			synchronized (monitor) {
				monitor.notify();
			}
			Thread.sleep(100);
			log.info("thread state(after notify)={}", thread.getState()); // TIMED_WAITING (깨어난 후 sleep(1000) 수행 중)

			thread.join();
			log.info("thread state(after join)={}", thread.getState()); // TERMINATED
		} catch (InterruptedException ie) {
			log.error("main()", ie);
		}
	}

	private static void checkThreadState2() {
		Object  monitor = new Object();
		StateThread thread = new StateThread(monitor);
		StateThread thread2 = new StateThread(monitor);

		try {
			log.info("thread state={}", thread.getState()); // NEW
			thread.start();
			thread2.start();
			log.info("thread state(after start)={}", thread.getState()); // RUNNABLE

			Thread.sleep(100);
			log.info("thread state(after 0.1 sec)={}", thread.getState()); // WAITING
			log.info("thread2 state(after 0.1 sec)={}", thread2.getState()); // WAITING

			// 이 상태에서는 thread와 thread2가 waiting 상태에 있다.
			synchronized (monitor) {
				monitor.notify();
			}
			Thread.sleep(100);
			log.info("thread state(after notify)={}", thread.getState()); // TIMED_WAITING (깨어난 후 sleep(1000) 수행 중)
			log.info("thread2 state(after notify)={}", thread2.getState()); // WAITING -> notify()는 하나의 thread만 깨운다. thread만 깨워진 상황

			thread.join();
			thread2.join(); // 이 라인으로 진입하지 않음. thread2는 계속 waiting 중임 => notify all을 사용해야 함
			log.info("thread state(after join)={}", thread.getState()); // ?
			log.info("thread2 state(after join)={}", thread2.getState()); // ?
		} catch (InterruptedException ie) {
			log.error("main()", ie);
		}
	}

	private static void checkThreadState3() {
		Object  monitor = new Object();
		StateThread thread = new StateThread(monitor);
		StateThread thread2 = new StateThread(monitor);

		try {
			log.info("thread state={}", thread.getState()); // NEW
			thread.start();
			thread2.start();
			log.info("thread state(after start)={}", thread.getState()); // RUNNABLE

			Thread.sleep(100);
			log.info("thread state(after 0.1 sec)={}", thread.getState()); // WAITING
			log.info("thread2 state(after 0.1 sec)={}", thread2.getState()); // WAITING

			// 이 상태에서는 thread와 thread2가 waiting 상태에 있다.
			synchronized (monitor) {
				monitor.notifyAll();
			}
			Thread.sleep(100);
			log.info("thread state(after notify)={}", thread.getState()); // TIMED_WAITING (깨어난 후 sleep(1000) 수행 중)
			log.info("thread2 state(after notify)={}", thread2.getState()); // TIMED_WAITING (깨어난 후 sleep(1000) 수행 중)

			thread.join();
			thread2.join();
			log.info("thread state(after join)={}", thread.getState()); // TERMINATED
			log.info("thread2 state(after join)={}", thread2.getState()); // TERMINATED
		} catch (InterruptedException ie) {
			log.error("main()", ie);
		}
	}
}
