import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class WaitThread.
 * Acquires lock over Object and waits till another thread notifies it. Meanwhile it releases lock when waiting, and reacquires it after being notified.
 */
public class WaitThread implements Runnable{

	/** The obj. */
	private Object obj;
	
	/**
	 * Instantiates a new wait thread.
	 *
	 * @param obj the obj
	 */
	public WaitThread(Object obj) {
		this.obj = obj;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		synchronized (obj) {
			try {
				System.out.println(dateFormat.format(new Date()) + " : " + Thread.currentThread().getName() + " : Waiting");
				obj.wait();
				System.out.println(dateFormat.format(new Date()) + " : " + Thread.currentThread().getName() + " : back to running.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
