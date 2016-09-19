package enhanced;

import resource.safe.Resource;

public class NumberIncrementerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new Resource(0);
		
		Thread1 r1 = new Thread1(resource);
		Thread2 r2 = new Thread2(resource);
		Thread3 r3 = new Thread3(resource);
		
		r1.setThread2(r2);
		r2.setThread3(r3);
		r3.setThread1(r1);
		
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();
	}

}
