import java.util.concurrent.Exchanger;


// TODO: Auto-generated Javadoc
/**
 * The Class Thread1.
 */
public class Thread1 implements Runnable {
	
	/** The exchanger. */
	private Exchanger<Integer> exchanger;
	
	/** The data. */
	private int data;
	
	/**
	 * Instantiates a new thread 1.
	 *
	 * @param exchanger the exchanger
	 * @param data the data
	 */
	public Thread1(Exchanger<Integer> exchanger, int data) {
		this.exchanger = exchanger;
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
