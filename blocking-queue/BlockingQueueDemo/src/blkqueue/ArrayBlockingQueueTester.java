package blkqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> bq1 = new ArrayBlockingQueue<Integer>(10);
		Thread pThread = new Thread(new PutThread(bq1));
		Thread rThread= new Thread(new RemoveThread(bq1));
		
		pThread.start();
		rThread.start();
		
	}

}
