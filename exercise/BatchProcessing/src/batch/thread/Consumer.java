package batch.thread;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 * Has the job to take string messages from final blocking queue and display them. After that sleep for 5 sec.
 */
public class Consumer implements Runnable {

	/** The blocking queue. */
	private BlockingQueue<String> bQueue;
	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param bq the blocking queue
	 */
	public Consumer(BlockingQueue<String> bq) {
		// TODO Auto-generated constructor stub
		bQueue = bq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String res = bQueue.take();
				System.out.println("\n" + res);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
