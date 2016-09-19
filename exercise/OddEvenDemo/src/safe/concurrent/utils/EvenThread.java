package safe.concurrent.utils;

import resource.safe.Resource;

public class EvenThread implements Runnable {

	private Resource resource;
	private OddThread oddThread;
	
	public EvenThread(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (this) {
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			int num = resource.getNumber() ;
			resource.setNumber(++num);
			System.out.println("Even : " + resource.getNumber());
			
			synchronized (oddThread) {
				oddThread.notify();
			}
		}
	}

	public void setOddThread(OddThread oddThread) {
		this.oddThread = oddThread;
	}

}
