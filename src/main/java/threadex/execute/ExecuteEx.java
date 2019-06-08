package threadex.execute;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ExecuteEx {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			Runnable runnable = () -> {
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
				int poolSize = threadPoolExecutor.getPoolSize();
				String threadName = Thread.currentThread().getName();
				log.info("[총 스레드 개수: {}] 작업 스레드 이름: {}", poolSize, threadName);

				// 의도적으로 exception을 발생시킴
				// - execute(task) => thread를 종료시킴
				// - submit(task) => thread를 종료시키지 않음
				int value = Integer.parseInt("삼");
			};

			/*
			[총 스레드 개수: 2] 작업 스레드 이름: pool-1-thread-1
			[총 스레드 개수: 2] 작업 스레드 이름: pool-1-thread-2
			...
			[총 스레드 개수: 2] 작업 스레드 이름: pool-1-thread-10
			 */
			//executorService.execute(runnable);
			/*
			[총 스레드 개수: 2] 작업 스레드 이름: pool-1-thread-2
			[총 스레드 개수: 2] 작업 스레드 이름: pool-1-thread-1
			...
			[총 스레드 개수: 2] 작업 스레드 이름: pool-1-thread-1
			 */
			executorService.submit(runnable);
		}

		executorService.shutdown();
	}
}
