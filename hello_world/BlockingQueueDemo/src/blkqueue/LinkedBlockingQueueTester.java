package blkqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedBlockingQueueTester.
 */
public class LinkedBlockingQueueTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> bq1 = new LinkedBlockingQueue<Integer>();
		Thread pThread = new Thread(new PutThread(bq1));
		Thread rThread= new Thread(new RemoveThread(bq1));
		
		pThread.start();
		rThread.start();
	}

}
