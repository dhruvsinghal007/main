package batch.svc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import batch.thread.Processor;
import batch.thread.Producer;
import batch.thread.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Class BatchProcessingDemo.
 * Creates two blocking queues, one for input from user, and other for processed inputs. Then create a producer thread, a consumer thread and a worker thread. Producer takes input from user (here just some initialized strings) and puts them in the input blocking queue, processor takes elements from input blocking queue and converts them into final outputs and also puts the final output in output blocking queue. The consumer finally takes the output from output queue and displays it. 
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
