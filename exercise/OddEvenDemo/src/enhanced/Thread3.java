package enhanced;

import resource.safe.Resource;

public class Thread3 implements Runnable {

	private Resource resource;
	
	public Thread3(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				int num ;
				if ((num = resource.getNumber()) % 3 == 2) {
					resource.setNumber(++num);
					System.out.println("t3 : " + resource.getNumber());
				}
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
