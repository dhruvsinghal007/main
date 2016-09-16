package enhanced;

import resource.Resource;

public class NumberIncrementerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource integer = new Resource(0);
		
		new Thread(new Thread1(integer)).start();
		new Thread(new Thread2(integer)).start();
		new Thread(new Thread3(integer)).start();
		
	}

}
