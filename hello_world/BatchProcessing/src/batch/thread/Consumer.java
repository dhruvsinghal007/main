package batch.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Consumer implements Runnable {

	private BlockingQueue<String> bQueue;
	
	public Consumer(BlockingQueue<String> bq) {
		// TODO Auto-generated constructor stub
		bQueue = bq;
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				String res = bQueue.take();
				System.out.println("\n" + res);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
