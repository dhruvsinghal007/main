package enhanced;

import resource.safe.Resource;

public class Thread2 implements Runnable {

	private Resource resource;
	private Thread3 thread3;
	
	public Thread2(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (this) {
				try {
					wait(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int num = resource.getNumber();
			resource.setNumber(++num);
			System.out.println("t2 : " + resource.getNumber());
			
			synchronized (thread3) {
				thread3.notify();
			}
		}
	}

	public void setThread3(Thread3 thread3) {
		this.thread3 = thread3;
	}

}
