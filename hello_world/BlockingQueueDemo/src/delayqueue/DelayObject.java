package delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayObject implements Delayed {
	
	private long startTime;
	private String message;
	private long delay;
	
	public DelayObject(long delay, String message) {
		this.startTime = System.currentTimeMillis() + delay;
		this.message = message;
		this.delay = delay;
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		if(startTime < ((DelayObject)o).startTime){
			return -1;
		}
		if(startTime > ((DelayObject)o).startTime){
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	public String toString(){
		return "[Message = " + message + " , Start Time = " + startTime + " Delay : " + delay + "]";
	}
	
	public String getMessage(){
		return message;
	}

}
