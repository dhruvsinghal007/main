package thread;

import java.util.concurrent.CountDownLatch;

// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 */
public class Worker implements Runnable {

	/** The countdown latch. */
	private CountDownLatch latch;
	
	/** The message. */
	private String msg;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param latch the countdown latch
	 * @param msg the message
	 */
	public Worker(CountDownLatch latch,String msg) {
		this.latch = latch;
		this.msg = msg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(msg);
		latch.countDown();
	}

}
