package delayqueue;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class DelayObjectConsumer.
 */
public class DelayObjectConsumer implements Runnable {
	
	/** The blocking queue. */
	private BlockingQueue<DelayObject> queue;

	/**
	 * Instantiates a new delay object consumer.
	 *
	 * @param queue the blocking queue
	 */
	public DelayObjectConsumer(BlockingQueue<DelayObject> queue) {
		this.queue = queue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				System.out.println("Removed : " + queue.take().getMessage() + " time : " + System.currentTimeMillis());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
