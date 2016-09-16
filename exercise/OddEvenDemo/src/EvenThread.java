import java.util.concurrent.Semaphore;

public class EvenThread implements Runnable {

	private Resource integer;
	private Semaphore semaphore;
	
	public EvenThread(Resource integer, Semaphore semaphore) {
		this.integer = integer;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				semaphore.acquire();
				int num;
				if ((num = integer.getNumber()) % 2 != 0) {
					integer.setNumber(++num); 
					System.out.println("Even : " + integer.getNumber());
				}
				semaphore.release();
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
