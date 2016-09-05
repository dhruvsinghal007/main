import java.util.concurrent.Exchanger;


public class Thread1 implements Runnable {
	
	private Exchanger<Integer> exchanger;
	private int data;
	
	public Thread1(Exchanger<Integer> exchanger, int data) {
		this.exchanger = exchanger;
		this.data = data;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int data = this.data;
		
		try {
			this.data = exchanger.exchange(this.data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " exchanged " + this.data + " for " + data);
	}

}
