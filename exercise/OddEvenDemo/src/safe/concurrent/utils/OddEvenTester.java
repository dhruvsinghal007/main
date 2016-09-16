package safe.concurrent.utils;

import resource.Resource;

public class OddEvenTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new Resource(0);
		
		new Thread(new OddThread(resource)).start();
		new Thread(new EvenThread(resource)).start();
		
	}

}
