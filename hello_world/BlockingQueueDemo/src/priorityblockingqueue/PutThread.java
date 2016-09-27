/**
 * 
 */
package priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The class PutThread
 * class to put string into priority blocking queue.
 *
 * @author Dhruv
 */
public class PutThread implements Runnable {

	/** The queue. */
	private PriorityBlockingQueue<String> queue;
	
	/**
	 * Instantiates a new put thread.
	 *
	 * @param queue the blocking queue
	 */
	public PutThread(PriorityBlockingQueue<String> queue) {
		this.queue = queue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; ; i++){
			String msg = "Message" + i;
			queue.put(msg);
			System.out.println("\nAdded : " + msg);
			for(String s : queue){
				System.out.print(s + " ");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
