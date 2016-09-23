import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SleepThread implements Runnable {
	
private Object obj;
	
	public SleepThread(Object obj) {
		this.obj = obj;
	}

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
