package batch.svc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import batch.thread.Processor;
import batch.thread.Producer;
import batch.thread.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Class BatchProcessingDemo.
 */
public class BatchProcessingDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<String> bq1 = new LinkedBlockingQueue<String>();
		BlockingQueue<String> bq2 = new LinkedBlockingQueue<String>();
				
		Producer thread1 = new Producer(bq1);
		Processor thread2 = new Processor(bq1,bq2);
		Consumer thread3 = new Consumer(bq2);
		
		new Thread(thread1).start();
		new Thread(thread2).start();
		new Thread(thread3).start();
		
	}

}
