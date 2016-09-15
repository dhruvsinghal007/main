import java.util.concurrent.atomic.AtomicInteger;


public class OddThread implements Runnable {

	private AtomicInteger integer;
	
	public OddThread(AtomicInteger integer) {
		this.integer = integer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (integer.get() % 2 == 0) {
				System.out.println("Odd : " + integer.incrementAndGet());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
