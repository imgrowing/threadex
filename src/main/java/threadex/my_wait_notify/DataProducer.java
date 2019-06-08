package threadex.my_wait_notify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataProducer extends Thread {
	private DataBox dataBox;

	public DataProducer(DataBox dataBox) {
		this.dataBox = dataBox;
		setName("Producer");
	}

	@Override
	public void run() {
		// produce
		for (int i = 1; i <= 3; i++) {
			String data = "Data-" + i;
			dataBox.setData(data);
			log.info("setData(): {}", data);
		}
	}
}
