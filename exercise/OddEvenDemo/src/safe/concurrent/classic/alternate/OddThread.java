package safe.concurrent.classic.alternate;

import resource.unsafe.Resource;

public class OddThread implements Runnable {

	private Resource resource;
	private EvenThread evenThread;
	
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

}
