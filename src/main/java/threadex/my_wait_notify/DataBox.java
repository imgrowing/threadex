package threadex.my_wait_notify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataBox {
	private String data;

	private DataConsumer consumer;
	private DataProducer producer;

	public void setConsumer(DataConsumer consumer, DataProducer producer) {
		this.consumer = consumer;
		this.producer = producer;
	}

	public synchronized String getData() {
		log.warn("getData() start - consumer state: {}", consumer.getState());
		log.warn("getData() start - producer state: {}", producer.getState());
		if (this.data == null) {
			try {
				log.warn("consumer - before wait()");
				wait(); // consumer thread
			} catch (InterruptedException e) {
			}
		}
		log.warn("getData() after wait - consumer state: {}", consumer.getState());
		log.warn("getData() after wait - producer state: {}", producer.getState());

		String returnValue = data;
		log.info("ConsumerThread가 읽은 데이터: {}", returnValue);
		data = null;
		notify(); // producer?? thread

		log.warn("getData() after notify - consumer state: {}", consumer.getState());
		log.warn("getData() after notify - producer state: {}", producer.getState());
		return returnValue;
	}

	public synchronized void setData(String data) {
		log.warn("setData() start - consumer state: {}", consumer.getState());
		log.warn("setData() start - producer state: {}", producer.getState());
		if (this.data != null) {
			try {
				log.warn("producer - before wait()");
				wait(); // producer thread
			} catch (InterruptedException e) {
			}
		}

		log.warn("setData() after wait - consumer state: {}", consumer.getState());
		log.warn("setData() after wait - producer state: {}", producer.getState());

		this.data = data;
		log.info("ProducerThread가 생산한 데이터: {}", data);
		notify();

		log.warn("setData() after notify - consumer state: {}", consumer.getState());
		log.warn("setData() after notify - producer state: {}", producer.getState());
	}
}
