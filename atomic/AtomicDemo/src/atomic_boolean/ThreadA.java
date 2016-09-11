package atomic_boolean;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadA implements Runnable {

	private AtomicBoolean resource;
	
	public ThreadA(AtomicBoolean resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + " : original value : " + resource.get());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		resource.compareAndSet(true, false);
		System.out.println(Thread.currentThread().getName() + " : new value : " + resource.get());
	}

}
