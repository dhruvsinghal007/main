package blkqueue;

import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class RemoveThread.
 */
public class RemoveThread implements Runnable {

	/** The b queue. */
	BlockingQueue<Integer> bQueue;
	
	/**
	 * Instantiates a new removes the thread.
	 *
	 * @param bQueue the blocking queue
	 */
	public RemoveThread(BlockingQueue<Integer> bQueue) {
		// TODO Auto-generated constructor stub
		this.bQueue = bQueue;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Integer res = bQueue.take();
				System.out.println("Element removed : " + res);
				Object[] arr = bQueue.toArray();
				
				for(Object ele : arr){
					System.out.print((Integer)ele + " ");
				}
				System.out.println();
				
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
