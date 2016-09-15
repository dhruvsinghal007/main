package batch.thread;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

	private BlockingQueue<String> outputQ;
	private String message;
	
	public Worker(BlockingQueue<String> bq, String msg) {
		// TODO Auto-generated constructor stub
		outputQ = bq;
		message = msg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String processedMsg = "Processed " + message + " by thread " + Thread.currentThread().getName();
		try {
			outputQ.put(processedMsg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
