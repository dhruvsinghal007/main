package thread;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * class to insert string elements in priority queue
 * @author Dhruv
 *
 */
public class Worker implements Runnable {

	private Queue<String> queue = new PriorityQueue<>();
	
	public Worker(Queue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; ; i++){
			String str = "Message" + i;
			queue.offer(str);
			System.out.println("\nAdded : " + str);
			for(String s : queue){
				System.out.print(s + " ");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
