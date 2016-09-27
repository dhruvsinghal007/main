import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class SleepThread.
 * Acquires lock over Object and sleeps for 10 sec.
 */
public class SleepThread implements Runnable {
	
/** The obj. */
private Object obj;
	
	/**
	 * Instantiates a new sleep thread.
	 *
	 * @param obj the obj
	 */
	public SleepThread(Object obj) {
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
			System.out.println(dateFormat.format(new Date())+" : "+Thread.currentThread().getName() + " : Sleeping");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
