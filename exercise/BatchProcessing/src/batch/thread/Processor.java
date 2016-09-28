package batch.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// TODO: Auto-generated Javadoc
/**
 * The Class Processor.
 */
public class Processor implements Runnable {

	/** The input blocking queue. */
	private BlockingQueue<String> inputBlockingQueue;
	
	/** The executor. */
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		}
	}

}
