package priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The class PriorityBlockingQueueTester
 * prints messages in natural order of their elements and treat queue as blocking queue.
 *
 * @author Dhruv
 */
public class PriorityBlockingQueueTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<String>();
		new Thread(new PutThread(queue)).start();
		new Thread(new TakeThread(queue)).start();
	}

}
