package corej;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class InvokeAllEx {
	public static void main(String[] args) {
		List<RangeSum> sums = new ArrayList<>();
		sums.add(new RangeSum(1, 99));
		sums.add(new RangeSum(100, 300));
		sums.add(new RangeSum(301, 555));
		sums.add(new RangeSum(556, 750));
		sums.add(new RangeSum(751, 1000));

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		try {
			List<Future<Integer>> futures = executorService.invokeAll(sums);
			log.warn("after invokeAll()");
			int sum = futures.stream()
				.map(future -> {
					try {
						log.warn("get()...");
						return future.get();
					} catch (Exception e) {
					}
					return 0;
				}).mapToInt(Integer::intValue).sum();
			log.warn("total sum: {}", sum);
		} catch (InterruptedException e) {
			log.error("invokeAll() is interrupted", e);
		}
	}

	@Slf4j
	public static class RangeSum implements Callable<Integer> {
		private int start;
		private int end;

		public RangeSum(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public Integer call() throws Exception {
			int sum = IntStream.range(start, end + 1).sum();
			Thread.sleep(1000); // 스레드 풀의 스레드가 어떻게 할당되는지 확인하기 위해 task의 수행시간을 강제로 늘림
			log.info("start: {} ~ end: {} = sum: {}", start, end, sum);
			return sum;
		}
	}
}
