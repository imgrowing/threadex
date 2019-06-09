package corej.safety;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VisibilityEx {

	private static boolean doneCpuCached = false;
	private static volatile boolean done = false;

	public static void main(String[] args) {
		NotCachedCase();
//		cpuCachedCase();
	}

	private static void cpuCachedCase() {
		Runnable hellos = () -> {
			for (int i = 0; i < 1000; i++) {
				log.info("Hello {}", i);
			}
			doneCpuCached = true;
		};

		Runnable goodbye = () -> {
			int i = 1;
			while (!doneCpuCached) { // 실제로 컴파일러가 최적화한 문장: if (!done) { while (true) i++; }
				// done 변수를 최초에 한 번만 읽어 사용하기 때문에, 결코 여기에 진입하지 않는다.
				i++;
			}
			log.warn("Goodbye {}", i);
		};

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(hellos);
		executorService.submit(goodbye);
	}

	private static void NotCachedCase() {
		Runnable hellos = () -> {
			for (int i = 0; i < 1000; i++) {
				log.info("Hello {}", i);
			}
			done = true;
		};

		Runnable goodbye = () -> {
			int i = 1;
			while (!done) {
				i++;
			}
			log.warn("Goodbye {}", i);
		};

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(hellos);
		executorService.submit(goodbye);
	}
}
