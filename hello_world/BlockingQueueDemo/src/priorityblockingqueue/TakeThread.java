/**
 * 
 */
package priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class TakeThread.
 *
 * @author Dhruv
 */
public class TakeThread implements Runnable {

	/** The queue. */
	private PriorityBlockingQueue<String> queue;
	
	/**
	 * Instantiates a new take thread.
	 *
	 * @param queue the blocking queue
	 */
	public TakeThread(PriorityBlockingQueue<String> queue) {
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
				String str = queue.take();
				System.out.println("\nRemoved : " + str);
				for(String s : queue){
					System.out.print(s + " ");
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
