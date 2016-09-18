package safe.concurrent.classic.alternate;

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
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (resource) {
				int num = resource.getNumber();
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
	}

}
