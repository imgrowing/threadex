package threadex.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalSample extends Thread {
	private int countDown;
	private int counter = 0;
	private static int sharedCount = 0;

	public ThreadLocalSample(int countDown) {
		this.countDown = countDown;
	}

	@Override
	public void run() {
		for (int i = 0; i < countDown; i++) {
			counter += 1;           // 각 스레드가 각각 소유하는 변수
			sharedCount += 1;       // 모든 스레드들이 공유하는 변수
			ThreadLocalContainer.increase();
		}
		log.info("local counter: " + counter);
		log.info("sharedCounter: " + sharedCount);
		OtherLogic.printThreadLocalCounter();
	}

	public static void main(String[] args) {
		ThreadLocalSample thread1 = new ThreadLocalSample(11111);
		ThreadLocalSample thread2 = new ThreadLocalSample(11112);
		ThreadLocalSample thread3 = new ThreadLocalSample(11113);

		thread1.start();
		thread2.start();
		thread3.start();
	}
}
