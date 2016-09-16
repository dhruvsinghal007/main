import java.util.concurrent.Semaphore;

public class OddThread implements Runnable {

	private Resource integer;
	private Semaphore semaphore;
	
	public OddThread(Resource integer, Semaphore semaphore) {
		this.integer = integer;
		this.semaphore = semaphore;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				semaphore.acquire();
				int num ;
				if ((num = integer.getNumber()) % 2 == 0) {
					integer.setNumber(++num);
					System.out.println("Odd : " + integer.getNumber());
				}
				semaphore.release();
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
