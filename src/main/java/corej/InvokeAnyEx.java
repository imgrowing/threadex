package corej;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@Slf4j
public class InvokeAnyEx {
	public static void main(String[] args) {
		List<RangeSum> sums = new ArrayList<>();
		sums.add(new RangeSum(1, 330));
		sums.add(new RangeSum(331, 455));
		sums.add(new RangeSum(456, 750));
		sums.add(new RangeSum(751, 1000));

		ExecutorService executorService = Executors.newFixedThreadPool(4);
		try {
			Integer fastestSum = executorService.invokeAny(sums);
			log.warn("after invokeAny()");
			log.warn("fastest sum: {}", fastestSum);
		} catch (InterruptedException e) {
			log.error("invokeAny() is interrupted", e);
		} catch (ExecutionException e) {
			log.error("invokeAny() is error", e);
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
			log.info("start: {} ~ end: {} = sum: {}", start, end, sum);
			Thread.sleep(end - start); // task 마다 수행 시간의 차이를 만들기 위해
			log.info("{} ~ {} - task completed.", start, end);
			return sum;
		}
	}
}
