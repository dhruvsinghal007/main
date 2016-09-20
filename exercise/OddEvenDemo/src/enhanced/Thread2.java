package enhanced;

import resource.safe.Resource;

public class Thread2 implements Runnable {

	private Resource resource;
	private Thread3 thread3;
	private boolean flag1, flag2;
	
	public Thread2(Resource resource) {
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
			System.out.println("t2 : " + resource.getNumber());
			
			while (thread3.getFlag() ==  false) {}
			
			synchronized (thread3) {
				thread3.notify();
				flag2 = true;
			}
		}
	}

	public void setThread3(Thread3 thread3) {
		this.thread3 = thread3;
	}
	
	public boolean getFlag(){
		return flag1;
	}

}
