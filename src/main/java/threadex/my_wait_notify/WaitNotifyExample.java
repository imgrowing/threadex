package threadex.my_wait_notify;

public class WaitNotifyExample {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();

		DataProducer dataProducer = new DataProducer(dataBox);
		DataConsumer dataConsumer = new DataConsumer(dataBox);
		dataBox.setConsumer(dataConsumer, dataProducer);

		dataProducer.start();
		dataConsumer.start();
	}
}
