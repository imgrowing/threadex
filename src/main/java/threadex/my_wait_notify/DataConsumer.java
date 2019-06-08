package threadex.my_wait_notify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataConsumer extends Thread {
	private DataBox dataBox;

	public DataConsumer(DataBox dataBox) {
		this.dataBox = dataBox;
		setName("Consumer");
	}

	@Override
	public void run() {
		// consume
		for (int i = 1; i <= 3; i++) {
			log.info("getData() : {}", dataBox.getData());
		}
	}
}
