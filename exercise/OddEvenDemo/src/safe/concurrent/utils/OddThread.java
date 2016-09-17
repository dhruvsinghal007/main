package safe.concurrent.utils;

import resource.safe.Resource;

public class OddThread implements Runnable {

	private Resource resource;
	
	public OddThread(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				int num ;
				if ((num = resource.getNumber()) % 2 == 0) {
					resource.setNumber(++num);
					System.out.println("Odd : " + resource.getNumber());
				}
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
