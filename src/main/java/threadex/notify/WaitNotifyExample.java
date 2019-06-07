package threadex.notify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitNotifyExample {
	public static void main(String[] args) {
		WorkObject sharedObject = new WorkObject();

		ThreadA threadA = new ThreadA(sharedObject);
		ThreadB threadB = new ThreadB(sharedObject);

		threadA.start();
		threadB.start();

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}

		log.info("threadA state: {}", threadA.getState());
		log.info("threadB state: {}", threadB.getState());

		synchronized (sharedObject) {
			log.info("notifyAll()");
			sharedObject.notifyAll();
		}

		log.info("threadA state: {}", threadA.getState());
		log.info("threadB state: {}", threadB.getState());
	}
}
