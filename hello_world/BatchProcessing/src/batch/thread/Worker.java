package batch.thread;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 */
public class Worker implements Runnable {

	/** The output blocking queue. */
	private BlockingQueue<String> outputBlockingQueue;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param bq the bq
	 * @param msg the msg
	 */
	public Worker(BlockingQueue<String> bq, String msg) {
		// TODO Auto-generated constructor stub
		outputBlockingQueue = bq;
		message = msg;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String processedMsg = "Processed " + message + " by thread " + Thread.currentThread().getName();
		try {
			outputBlockingQueue.put(processedMsg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
