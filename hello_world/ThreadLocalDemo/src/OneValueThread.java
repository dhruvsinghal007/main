
// TODO: Auto-generated Javadoc
/**
 * The Class OneValueThread.
 */
public class OneValueThread implements Runnable {

	/** The thread local instance. */
	private ThreadLocal<Integer> threadLocal;
	
	/**
	 * Instantiates a new one value thread.
	 *
	 * @param threadLocal the thread local
	 */
	public OneValueThread(ThreadLocal<Integer> threadLocal) {
		this.threadLocal = threadLocal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadLocal.set(1);
		System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
	}

}
