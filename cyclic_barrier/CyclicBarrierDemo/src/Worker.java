import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class Worker implements Runnable {

	private CyclicBarrier barrier;
	
	public Worker(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Waiting : " + Thread.currentThread().getName());
		try {
			Thread.sleep((long) (Math.random() * 10000));
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Barrier crossed. Now resuming execution..." + Thread.currentThread().getName());
		
	}

}
