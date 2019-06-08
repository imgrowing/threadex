package threadex.stop_thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintThread3 extends Thread {
	@Override
	public void run() {
		while (true) {
			log.info("실행 중");

			if (isInterrupted()) {
				break;
			}
		}

		log.info("자원 정리");
		log.info("실행 종료");
	}
}
