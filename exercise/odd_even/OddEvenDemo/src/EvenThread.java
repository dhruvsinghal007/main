import java.util.concurrent.atomic.AtomicInteger;


public class EvenThread implements Runnable {

	private AtomicInteger integer;
	
	public EvenThread(AtomicInteger integer) {
		this.integer = integer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (integer.get() % 2 != 0) {
				System.out.println("Even : " + integer.incrementAndGet());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
