package safe.concurrent.classic;

import resource.unsafe.Resource;

public class OddEvenTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new Resource(0);
		
		OddThread oddThread = new OddThread(resource);
		EvenThread evenThread = new EvenThread(resource);
		
		oddThread.setEvenThread(evenThread);
		evenThread.setOddThread(oddThread);
		
		new Thread(oddThread).start();
		new Thread(evenThread).start();
		
	}

}
