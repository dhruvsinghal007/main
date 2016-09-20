package safe.concurrent.classic.alternate;

import resource.unsafe.Resource;

public class OddThread implements Runnable {

	private Resource resource;
	private EvenThread evenThread;
	private boolean wFlag1, wFlag2;
	
	public OddThread(Resource resource) {
		this.resource = resource;
	}

	public void setEvenThread(EvenThread evenThread) {
		this.evenThread = evenThread;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (resource) {
				int num = resource.getNumber();
				resource.setNumber(++num);
				System.out.println("Odd : " + resource.getNumber());
			}
			
			while(evenThread.isWChanged() == false){}
			
			synchronized (evenThread) {
				evenThread.notify();
			}
			synchronized (this) {
				wFlag2 = wFlag1;
				wFlag1 = wFlag1 ? false : true;
				
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				wFlag2 = wFlag1;
			}
		}
	}
	
	public boolean isWChanged(){
		return (!(wFlag1 == wFlag2));
	}
	
}
