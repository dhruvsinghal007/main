package blkqueue;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class PutThread.
 */
public class PutThread implements Runnable{

	/** The b queue. */
	BlockingQueue<Integer> bQueue;
	
	/**
	 * Instantiates a new put thread.
	 *
	 * @param bq the blocking queue
	 */
	public PutThread(BlockingQueue<Integer> bq) {
		// TODO Auto-generated constructor stub
		bQueue = bq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		int i = 1;
		while(i > 0){
			try {
				bQueue.put(i);
				System.out.println("Element added : " + i);
				Object[] arr = bQueue.toArray();
				
				for(Object ele : arr){
					System.out.print((Integer)ele + " ");
				}
				System.out.println();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	
}
