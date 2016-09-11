package atomic_boolean;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * creating two threads to examine the behavior of change in the value of atomic boolean.
 * @author Dhruv
 *
 */
public class AtomicBooleanTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtomicBoolean resource = new AtomicBoolean(true);
		new Thread(new ThreadA(resource)).start();
		new Thread(new ThreadB(resource)).start();
	}
}
