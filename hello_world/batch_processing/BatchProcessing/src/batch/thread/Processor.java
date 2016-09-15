package batch.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Processor implements Runnable {

	private BlockingQueue<String> inputQ;
	private ThreadPoolExecutor executor;
	private BlockingQueue<String> outputQ;
	
	public Processor(BlockingQueue<String> bq , 
						BlockingQueue<String> res ) {
		// TODO Auto-generated constructor stub
		inputQ = bq;
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(7);
		outputQ = res;
	}
	
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String str = inputQ.take();
				
				executor.submit(new Worker(outputQ, str));
				
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		}
	}

}
