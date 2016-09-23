import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WaitThread implements Runnable{

	private Object obj;
	
	public WaitThread(Object obj) {
		this.obj = obj;
	}

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
