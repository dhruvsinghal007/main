import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 * TRies to acquire locks of Object the sleeping and waiting threads have already acquired. Also notifies the waiting thread.
 */
public class Worker implements Runnable {

	/** The obj 2. */
	private Object obj1,obj2;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param obj1 the obj 1
	 * @param obj2 the obj 2
	 */
	public Worker(Object obj1, Object obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(new Date()) + " : Testing wait...");
		synchronized (obj1) {
			System.out.println(dateFormat.format(new Date()) + " Waiting object being notified !");
			obj1.notify();
			System.out.println(dateFormat.format(new Date()) + " Waiting object notified !");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(dateFormat.format(new Date()) + " Testing sleep...Trying to acquire lock...");
		synchronized (obj2) {
			System.out.println(dateFormat.format(new Date()) + " Lock acquired.");
		}
		System.out.println(dateFormat.format(new Date()) + " Lock released.");
	}

}
