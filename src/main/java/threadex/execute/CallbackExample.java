package threadex.execute;

import lombok.extern.slf4j.Slf4j;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
	private ExecutorService executorService;
	private CompletionHandler<Integer, Void> callback = new CompletionHandlerImpl();

	public CallbackExample() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

	public void doWork(final String x, final String y) {
		Runnable task = () -> {
			try {
				int intX = Integer.parseInt(x);
				int intY = Integer.parseInt(y);
				int result = intX + intY;
				callback.completed(result, null);
			} catch (NumberFormatException e) {
				callback.failed(e, null);
			}
		};

		executorService.submit(task);
	}

	public void finish() {
		executorService.shutdown();
	}

	public static void main(String[] args) {
		CallbackExample example = new CallbackExample();
		example.doWork("3", "3");
		example.doWork("3", "삼");
		example.finish();
	}

	@Slf4j
	public static class CompletionHandlerImpl implements CompletionHandler<Integer, Void> {
		@Override
		public void completed(Integer result, Void attachment) {
			log.info("completed() 실행: {}", result);
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			log.info("failed() 실행: {}", exc.getMessage());
		}
	}
}
