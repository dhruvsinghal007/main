package batch.thread;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
