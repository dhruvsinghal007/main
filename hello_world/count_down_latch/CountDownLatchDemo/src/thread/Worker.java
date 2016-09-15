package thread;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	private CountDownLatch latch;
	private String msg;
	
	public Worker(CountDownLatch latch,String msg) {
		this.latch = latch;
		this.msg = msg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(msg);
		latch.countDown();
	}

}
