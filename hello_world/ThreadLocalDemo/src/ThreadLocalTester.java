
// TODO: Auto-generated Javadoc
/**
 * The Class ThreadLocalTester.
 */
public class ThreadLocalTester {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
		threadLocal.set(0);
		new Thread(new OneValueThread(threadLocal),"OneValueThread").start();
		new Thread(new TwoValueThread(threadLocal),"TwoValueThread").start();
		new Thread(new ThreeValueThread(threadLocal),"ThreeValueThread").start();
		System.out.println("Main thread has value : " + threadLocal.get());
	}
}
