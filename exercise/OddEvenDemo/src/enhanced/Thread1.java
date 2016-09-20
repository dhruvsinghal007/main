package enhanced;

import resource.safe.Resource;

public class Thread1 implements Runnable {

	private Resource resource;
	private Thread2 thread2;
	private boolean flag1, flag2;
	
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
			
			while(thread2.getFlag() == false){}
			
			synchronized (thread2) {
				flag2 = true;
				thread2.notify();
				flag2 = false;
			}
			
			synchronized (this) {
				flag1 = true;
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag1 = false;
			}
			
		}
	}

	public void setThread2(Thread2 thread2) {
		this.thread2 = thread2;
	}

	public boolean getFlag1() {
		return flag1;
	}
	
	public boolean getFlag2(){
		return flag2;
	}

}
