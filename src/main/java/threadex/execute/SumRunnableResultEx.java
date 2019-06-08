package threadex.execute;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class SumRunnableResultEx {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		log.warn("작업 처리 요청");

		Runnable task = () -> {
			int sum = 0;
			for (int i = 1; i <= 10; i++) {
				sum += i;
			}
			log.info("처리 결과 : {}", sum);
			throw new RuntimeException();
		};

		Future future = executorService.submit(task);

		try {
			future.get();
			log.warn("작업 처리 완료");
		} catch (InterruptedException e) {
			log.error("interrupted", e);
		} catch (ExecutionException e) {
			log.error("executionException", e);
		}

		executorService.shutdown();
		log.warn("main 스레드 종료");
	}
}
