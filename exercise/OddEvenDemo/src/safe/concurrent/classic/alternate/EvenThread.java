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
			synchronized (resource) {
				int num = resource.getNumber();
				resource.setNumber(++num);
				System.out.println("Even : " + resource.getNumber());
			}
			synchronized (oddThread) {
				oddThread.notify();
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
