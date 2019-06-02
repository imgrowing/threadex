package threadex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableSample implements Runnable {
	@Override
	public void run() {
		log.info("This is RunnableSample's run() method.");
	}
}
