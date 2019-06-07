package threadex.notify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkObject {
	public synchronized void methodA() {
		log.info("ThreadA의 methodA() 작업 실행");
		notify();

		try {
			wait();
		} catch (InterruptedException e) {
		}
	}

	public synchronized void methodB() {
		log.info("ThreadB의 methodB() 작업 실행");
		notify();

		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
}
