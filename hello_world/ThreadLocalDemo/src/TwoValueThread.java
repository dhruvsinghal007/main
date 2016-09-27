
// TODO: Auto-generated Javadoc
/**
 * The Class TwoValueThread.
 */
public class TwoValueThread implements Runnable {

	/** The thread local instance. */
	private ThreadLocal<Integer> threadLocal;
	
	/**
	 * Instantiates a new two value thread.
	 *
	 * @param threadLocal the thread local
	 */
	public TwoValueThread(ThreadLocal<Integer> threadLocal) {
		this.threadLocal = threadLocal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadLocal.set(2);
		System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
	}

}
