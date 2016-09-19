package safe.concurrent.classic.alternate;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import resource.unsafe.Resource;

public class OddEvenTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new Resource(0);
		
		OddThread oddThread = new OddThread(resource);
		EvenThread evenThread = new EvenThread(resource);
		
		oddThread.setEvenThread(evenThread);
		evenThread.setOddThread(oddThread);
		
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);
		executor.schedule(oddThread, 0, TimeUnit.MILLISECONDS);
		executor.schedule(evenThread, 1000, TimeUnit.MILLISECONDS);
		
	}

}
