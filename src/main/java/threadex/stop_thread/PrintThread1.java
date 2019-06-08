package threadex.stop_thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintThread1 extends Thread {
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (!stop) {
			log.info("실행 중");
		}
		
		log.info("자원 정리");
		log.info("실행 종료");
	}
}
