/**
 * 
 */
package priority_blocking_queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Dhruv
 *
 */
public class TakeThread implements Runnable {

	private PriorityBlockingQueue<String> queue;
	
	public TakeThread(PriorityBlockingQueue<String> queue) {
		this.queue = queue;
	}


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
