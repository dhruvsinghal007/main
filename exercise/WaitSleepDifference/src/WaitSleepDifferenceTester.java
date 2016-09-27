
// TODO: Auto-generated Javadoc
/**
 * The Class WaitSleepDifferenceTester.
 */
public class WaitSleepDifferenceTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object obj1 = new Object();
		Object obj2 = new Object();
		new Thread(new WaitThread(obj1), "WaitThread").start();
		new Thread(new SleepThread(obj2), "SleepThread").start();
		new Thread(new Worker(obj1, obj2), "Worker").start();
	}

}
