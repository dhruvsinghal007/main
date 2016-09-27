package blkqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class ArrayBlockingQueueTester.
 */
public class ArrayBlockingQueueTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> bq1 = new ArrayBlockingQueue<Integer>(10);
		Thread pThread = new Thread(new PutThread(bq1));
		Thread rThread= new Thread(new RemoveThread(bq1));
		
		pThread.start();
		rThread.start();
		
	}

}
