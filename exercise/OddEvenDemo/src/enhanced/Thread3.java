package enhanced;

import resource.safe.Resource;

public class Thread3 implements Runnable {

	private Resource resource;
	private Thread1 thread1;
	
	public Thread3(Resource resource) {
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
			System.out.println("t3 : " + resource.getNumber());
			
			synchronized (thread1) {
				thread1.notify();
			}
		}
	}

	public void setThread1(Thread1 thread1) {
		this.thread1 = thread1;
	}

}
