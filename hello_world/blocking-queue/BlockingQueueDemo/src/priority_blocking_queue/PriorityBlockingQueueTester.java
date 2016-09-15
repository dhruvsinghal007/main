package priority_blocking_queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * print messages in natural order of their elements and treat queue as blocking queue
 * @author Dhruv
 *
 */
public class PriorityBlockingQueueTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<String>();
		new Thread(new PutThread(queue)).start();
		new Thread(new TakeThread(queue)).start();
	}

}
