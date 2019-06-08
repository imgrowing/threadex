package threadex.execute;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ResultByRunnableEx {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		Result result = new Result();

		Task task1 = new Task(result);
		Task task2 = new Task(result);

		Future<Result> future1 = executorService.submit(task1, result);
		Future<Result> future2 = executorService.submit(task2, result);

		try {
			Result result1 = future1.get();
			log.info("result1: {}", result1.getAccumulatedSum());
			Result result2 = future2.get();
			log.info("result2: {}", result2.getAccumulatedSum());
			log.info("result: {}", result.getAccumulatedSum());
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
	}

	public static class Result {
		private AtomicInteger accumulatedSum = new AtomicInteger(0);

		public void add(int sum) {
			accumulatedSum.addAndGet(sum);
		}

		public int getAccumulatedSum() {
			return accumulatedSum.get();
		}
	}

	public static class Task implements Runnable {
		private Result result;

		public Task(Result result) {
			this.result = result;
		}

		@Override
		public void run() {
			for (int i = 1; i <= 1000; i++) {
				result.add(i);
			}
		}
	}
}
