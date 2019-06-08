package threadex.daemon;

import lombok.extern.slf4j.Slf4j;
import threadex.notify.ThreadB;

@Slf4j
public class AutoSaveThread extends Thread {
	public void save() {
		log.info("작업 내용을 저장함.");
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.warn("interrupted : {}", e.getMessage());
				break;
			}

			save();
		}
	}
}
