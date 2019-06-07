package threadex.thread;

public class ThreadLocalContainer {
	private final static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

	public static void increase() {
		local.set(local.get() + 1);
	}

	public static int get() {
		return local.get();
	}
}
