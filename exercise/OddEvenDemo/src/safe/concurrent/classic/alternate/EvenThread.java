package safe.concurrent.classic.alternate;

import resource.unsafe.Resource;

public class EvenThread implements Runnable {

	private Resource resource;
	private OddThread oddThread;
	private boolean wFlag1, wFlag2;
	
	public EvenThread(Resource resource) {
		this.resource = resource;
	}

	public void setOddThread(OddThread oddThread) {
		this.oddThread = oddThread;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
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
			synchronized (resource) {
				int num = resource.getNumber();
				resource.setNumber(++num);
				System.out.println("Even : " + resource.getNumber());
			}
			
			while(oddThread.isWChanged() == false){}
			
			synchronized (oddThread) {
				oddThread.notify();
			}
		}
	}

	public boolean isWChanged(){
		return (!(wFlag1 == wFlag2));
	}
	
}
