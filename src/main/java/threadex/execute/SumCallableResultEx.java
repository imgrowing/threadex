package threadex.execute;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SumCallableResultEx {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		log.warn("작업 처리 요청");

		Callable<Integer> task = () -> {
			int sum = 0;
			for (int i = 1; i <= 10; i++) {
				sum += i;
			}
			log.info("처리 결과 : {}", sum);
			return sum;
		};

		Future<Integer> future = executorService.submit(task);

		try {
			Integer sum = future.get();
			log.warn("작업 처리 완료: {}", sum);
		} catch (InterruptedException e) {
			log.error("interrupted", e);
		} catch (ExecutionException e) {
			log.error("executionException", e);
		}

		executorService.shutdown();
		log.warn("main 스레드 종료");
	}
}
