package batch.thread;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Producer.
 * Takes input, then creates a new worker thread to do the job of putting string message into input blocking queue.
 */
public class Producer implements Runnable{

	/** The input blocking queue. */
	private BlockingQueue<String> inputBlockingQueue;
	
	/**
	 * Gets the blocking queue reference.
	 *
	 * @return the bQueue
	 */
	public BlockingQueue<String> getbQueue() {
		return inputBlockingQueue;
	}

	/**
	 * Instantiates a new producer.
	 *
	 * @param bq the blocking queue
	 */
	public Producer(BlockingQueue<String> bq) {
		// TODO Auto-generated constructor stub
		inputBlockingQueue = bq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		int i = 1;
		while(true){
			try {
				String msg = "Message " + i;
				System.out.println("\nString added : " + msg);
				inputBlockingQueue.put(msg);
				for(String str : inputBlockingQueue){
					System.out.print(str + " ");
				}
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}

}
