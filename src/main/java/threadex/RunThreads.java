package threadex;

public class RunThreads {
	public static void main(String[] args) {
		RunThreads threads = new RunThreads();
		threads.runBasic();
	}

	private void runBasic() {
		RunnableSample runnable = new RunnableSample();
		ThreadSample thread = new ThreadSample();

		new Thread(runnable).start(); // Runnable 실행 방법
		thread.start(); // Thread 실행 방법
	}
}
