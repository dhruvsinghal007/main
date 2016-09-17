package enhanced;

import resource.safe.Resource;

public class Thread1 implements Runnable {

	private Resource resource;
	
	public Thread1(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				int num ;
				if ((num = resource.getNumber()) % 3 == 0) {
					resource.setNumber(++num);
					System.out.println("t1 : " + resource.getNumber());
				}
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
