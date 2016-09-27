package delayqueue;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class DelayObjectProducer.
 */
public class DelayObjectProducer implements Runnable {
	
	/** The blocking queue. */
	private BlockingQueue<DelayObject> queue;

	/**
	 * Instantiates a new delay object producer.
	 *
	 * @param queue the blocking queue
	 */
	public DelayObjectProducer(BlockingQueue<DelayObject> queue) {
		this.queue = queue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true){
			DelayObject obj = new DelayObject((long) (Math.random() * 5000), "Object" + i++);
			System.out.println("Added : " + obj);
			try {
				queue.put(obj);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
