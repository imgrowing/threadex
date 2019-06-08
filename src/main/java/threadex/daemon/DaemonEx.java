package threadex.daemon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemonEx {
	public static void main(String[] args) {
		AutoSaveThread thread = new AutoSaveThread();
		thread.setDaemon(true);

		thread.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}

		log.info("메인 스레드 종료");
	}
}
