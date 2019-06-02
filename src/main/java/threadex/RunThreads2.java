package threadex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunThreads2 {
	public static void main(String[] args) {
		RunThreads2 threads = new RunThreads2();
		threads.runBasic2();
	}

	private void runBasic2() {
		RunnableSample[] runnable = new RunnableSample[5];
		ThreadSample[] thread = new ThreadSample[5];

		for (int loop = 0; loop < 5; loop++) {
			runnable[loop] = new RunnableSample();
			thread[loop] = new ThreadSample();

			new Thread(runnable[loop]).start();
			thread[loop].start();
		}

		log.info("RunThreads.runBasic2() method is ended.");
		log.info("RunThreads.runBasic2() method is ended.");
	}
}
