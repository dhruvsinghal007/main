
// TODO: Auto-generated Javadoc
/**
 * The Class ThreeValueThread.
 */
public class ThreeValueThread implements Runnable {

	/** The thread local instance. */
	private ThreadLocal<Integer> threadLocal;
	
	/**
	 * Instantiates a new three value thread.
	 *
	 * @param threadLocal the thread local
	 */
	public ThreeValueThread(ThreadLocal<Integer> threadLocal) {
		this.threadLocal = threadLocal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadLocal.set(3);
		System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
	}

}
