package threadex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadSample extends Thread {
	@Override
	public void run() {
		log.info("This is ThreadSample's run() method.");
	}
}
