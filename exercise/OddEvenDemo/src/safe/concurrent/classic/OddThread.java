package safe.concurrent.classic;

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
			int num ;
			if ((num = resource.getNumber()) % 2 == 0) {
				synchronized (resource) {
					resource.setNumber(++num);
					System.out.println("Odd : " + resource.getNumber());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				synchronized (evenThread) {
					evenThread.notify();
				}
			}
			else{
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
