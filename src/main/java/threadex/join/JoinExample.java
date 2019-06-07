package threadex.join;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinExample {
	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();

		try {
			sumThread.join();
		} catch (InterruptedException ie) {
		}

		log.info("1~100 합: {}", sumThread.getSum());
	}
}
