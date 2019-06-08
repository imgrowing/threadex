package threadex.stop_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PrintThread2 extends Thread {
	@Override
	public void run() {
		try {
			while (true) {
				// InterruptedException은 스레드가 현재 WAITING 상태일 때만 발생한다.
				// 만일 RUNNABLE 상태일 때 interrupt()가 호출되면,
				// 미래에 WAITING 으로 진입하는 시점에서 발생하게 된다.
				log.info("실행 중");
				for (int i = 0; i < 100000000; i++) {
					String a = new String(String.valueOf(i)); // 시간을 지연시키기 위한 코드
				}
				log.info("before sleep()");
				TimeUnit.SECONDS.sleep(1);
				log.info("after sleep()");
			}
		} catch (InterruptedException e) {
			log.info("Interrupted: {}", e.getMessage());
		}
		
		log.info("자원 정리");
		log.info("실행 종료");
	}
}
