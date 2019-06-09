package corej;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class GreetingByeEx {
	public static void main(String[] args) {
		Runnable hellos = () -> {
			for (int i = 1; i <= 1000; i++) {
				log.info("Hello " + i);
			}
		};
		Runnable goodbyes = () -> {
			for (int i = 1; i <= 1000; i++) {
				log.info("Goodbye " + i);
			}
		};

		Executor executor = Executors.newCachedThreadPool();
		executor.execute(hellos);
		executor.execute(goodbyes);
	}
}
