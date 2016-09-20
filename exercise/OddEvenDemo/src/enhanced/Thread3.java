package enhanced;

import resource.safe.Resource;

public class Thread3 implements Runnable {

	private Resource resource;
	private Thread1 thread1;
	private boolean flag1,flag2;
	
	public Thread3(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			flag2 = false;
			
			synchronized (this) {
				flag1 = true;
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag1 = false;
			}
			
			int num = resource.getNumber();
			resource.setNumber(++num);
			System.out.println("t3 : " + resource.getNumber());
			
			while(thread1.getFlag() == false){}
			
			synchronized (thread1) {
				thread1.notify();
				flag2 = true;
			}
			
		}
	}

	public void setThread1(Thread1 thread1) {
		this.thread1 = thread1;
	}
	
	public boolean getFlag(){
		return flag1;
	}

}
