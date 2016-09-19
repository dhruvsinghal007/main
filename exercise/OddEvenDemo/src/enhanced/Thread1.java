package enhanced;

import resource.safe.Resource;

public class Thread1 implements Runnable {

	private Resource resource;
	private Thread2 thread2;
	
	public Thread1(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
			int num = resource.getNumber();
			resource.setNumber(++num);
			System.out.println("t1 : " + resource.getNumber());
			
			synchronized (thread2) {
				thread2.notify();
			}
			synchronized (this) {
				try {
					wait(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setThread2(Thread2 thread2) {
		this.thread2 = thread2;
	}

}
