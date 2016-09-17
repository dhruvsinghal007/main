package safe.concurrent.classic;

import resource.unsafe.Resource;

public class EvenThread implements Runnable {

	private Resource resource;
	private OddThread oddThread;
	
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
			int num ;
			if ((num = resource.getNumber()) % 2 == 1) {
				synchronized (resource) {
					resource.setNumber(++num);
					System.out.println("Even : " + resource.getNumber());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				synchronized (oddThread) {
					oddThread.notify();
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
