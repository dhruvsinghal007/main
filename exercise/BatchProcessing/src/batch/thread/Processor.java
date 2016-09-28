package batch.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// TODO: Auto-generated Javadoc
/**
 * The Class Processor.
 * Takes string from input blocking queue, processes them and then creates a new worker to do the job of processing string and putting into output blocking queue.
 */
public class Processor implements Runnable {

	/** The input blocking queue. */
	private BlockingQueue<String> inputBlockingQueue;
	
	/** The executor to create threads. */
	private ThreadPoolExecutor executor;
	
	/** The output blocking queue. */
	private BlockingQueue<String> outputBlockingQueue;
	
	/**
	 * Instantiates a new processor.
	 *
	 * @param bq the blocking queue
	 * @param res the blocking queue
	 */
	public Processor(BlockingQueue<String> bq , 
						BlockingQueue<String> res ) {
		// TODO Auto-generated constructor stub
		inputBlockingQueue = bq;
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(7);
		outputBlockingQueue = res;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String str = inputBlockingQueue.take();
				
				executor.submit(new Worker(outputBlockingQueue, str));
				
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				
		}
	}

}
