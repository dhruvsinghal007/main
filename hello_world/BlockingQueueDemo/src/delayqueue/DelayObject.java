package delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class DelayObject.
 */
public class DelayObject implements Delayed {
	
	/** The start time. */
	private long startTime;
	
	/** The message. */
	private String message;
	
	/** The delay. */
	private long delay;
	
	/**
	 * Instantiates a new delay object.
	 *
	 * @param delay the delay
	 * @param message the message
	 */
	public DelayObject(long delay, String message) {
		this.startTime = System.currentTimeMillis() + delay;
		this.message = message;
		this.delay = delay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "[Message = " + message + " , Start Time = " + startTime + " Delay : " + delay + "]";
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage(){
		return message;
	}

}
