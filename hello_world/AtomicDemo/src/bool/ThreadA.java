package bool;

import java.util.concurrent.atomic.AtomicBoolean;

// TODO: Auto-generated Javadoc
/**
 * The Class ThreadA.
 */
public class ThreadA implements Runnable {

	/** The resource. */
	private AtomicBoolean resource;
	
	/**
	 * Instantiates a new thread A.
	 *
	 * @param resource the resource
	 */
	public ThreadA(AtomicBoolean resource) {
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + " : original value : " + resource.get());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		resource.compareAndSet(true, false);
		System.out.println(Thread.currentThread().getName() + " : new value : " + resource.get());
	}

}
