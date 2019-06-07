package threadex.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtherLogic {
	public static void printThreadLocalCounter() {
		// 다른 클래스에서도 별도의 레퍼런스 없이 ThreadLocal에 바로 접근할 수 있다.
		log.info("thread-local counter: " + ThreadLocalContainer.get());
	}
}
