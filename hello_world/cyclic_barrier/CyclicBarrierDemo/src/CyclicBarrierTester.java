import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("All threads crossed barrier... Their execution is resumed...");
				try {
					Thread.sleep((long) (Math.random() * 10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		new Thread(new Worker(barrier), "Thread1").start();
		new Thread(new Worker(barrier), "Thread2").start();
		new Thread(new Worker(barrier), "Thread3").start();
	}

}
