package safe.concurrent.utils;

import resource.safe.Resource;

public class EvenThread implements Runnable {

	private Resource resource;
	
	public EvenThread(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				int num ;
				if ((num = resource.getNumber()) % 2 == 1) {
					resource.setNumber(++num);
					System.out.println("Even : " + resource.getNumber());
				}
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
