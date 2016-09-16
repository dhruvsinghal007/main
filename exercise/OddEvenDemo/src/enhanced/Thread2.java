package enhanced;

import resource.Resource;

public class Thread2 implements Runnable {

	private Resource resource;
	
	public Thread2(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				int num ;
				if ((num = resource.getNumber()) % 3 == 1) {
					resource.setNumber(++num);
					System.out.println("t2 : " + resource.getNumber());
				}
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
