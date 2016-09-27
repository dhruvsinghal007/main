package blkqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class SynchronousQueueTester.
 */
public class SynchronousQueueTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> bq1 = new SynchronousQueue<Integer>();
		Thread pThread = new Thread(new PutThread(bq1));
		Thread rThread= new Thread(new RemoveThread(bq1));
		
		pThread.start();
		rThread.start();
		
	}

}
