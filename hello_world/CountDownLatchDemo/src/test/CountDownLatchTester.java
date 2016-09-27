package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import thread.Worker;

// TODO: Auto-generated Javadoc
/**
 * Class CountDownLatchTester
 * class to implement CountDownLatch for three threads. Main thread keeps waiting
 *  for the five threads to complete their job. It prints the final message after
 *  then
 */

public class CountDownLatchTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountDownLatch latch = new CountDownLatch(5);
		ScheduledThreadPoolExecutor ex = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
		for(int i = 1 ; i <= 5 ; i++){
			String msg = "Message by Thread " + i;
			ex.schedule(new Thread(new Worker(latch, msg)), 1000 * i, TimeUnit.MILLISECONDS);
		}
		try {
			latch.await();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Final message by main thread.");
	}

}
