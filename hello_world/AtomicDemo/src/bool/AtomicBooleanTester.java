package bool;

import java.util.concurrent.atomic.AtomicBoolean;

// TODO: Auto-generated Javadoc
/**
 * The AtomicBooleanTester class
 * creating two threads to examine the behavior of change in the value of atomic boolean.
 * @author Dhruv
 *
 */
public class AtomicBooleanTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtomicBoolean resource = new AtomicBoolean(true);
		new Thread(new ThreadA(resource)).start();
		new Thread(new ThreadB(resource)).start();
	}
}
