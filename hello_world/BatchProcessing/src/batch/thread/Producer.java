package batch.thread;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Producer.
 */
public class Producer implements Runnable{

	/** The blocking queue bQueue. */
	private BlockingQueue<String> bQueue;
	
	/**
	 * Gets the blocking queue reference.
	 *
	 * @return the bQueue
	 */
	public BlockingQueue<String> getbQueue() {
		return bQueue;
	}

	/**
	 * Instantiates a new producer.
	 *
	 * @param bq the blocking queue
	 */
	public Producer(BlockingQueue<String> bq) {
		// TODO Auto-generated constructor stub
		bQueue = bq;
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
				bQueue.put(msg);
				for(String str : bQueue){
					System.out.print(str + " ");
				}
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

}
