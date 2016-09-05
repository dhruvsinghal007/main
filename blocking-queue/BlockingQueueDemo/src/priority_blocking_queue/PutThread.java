/**
 * 
 */
package priority_blocking_queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * class to put string into priority blocking queue
 * @author Dhruv
 *
 */
public class PutThread implements Runnable {

	private PriorityBlockingQueue<String> queue;
	
	public PutThread(PriorityBlockingQueue<String> queue) {
		this.queue = queue;
	}

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
