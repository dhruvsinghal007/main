package safe.concurrent.utils;

import resource.safe.Resource;

public class OddThread implements Runnable {

	private Resource resource;
	private EvenThread evenThread;
	
	public OddThread(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int num = resource.getNumber() ;
			resource.setNumber(++num);
			System.out.println("Odd : " + resource.getNumber());
			
			synchronized (evenThread) {
				evenThread.notify();
			}
			
			synchronized (this) {
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setEvenThread(EvenThread evenThread) {
		this.evenThread = evenThread;
	}

}
