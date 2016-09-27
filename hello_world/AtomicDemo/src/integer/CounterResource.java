package integer;

import java.util.concurrent.atomic.AtomicInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class CounterResource.
 */
public class CounterResource {
	
	/** The integer. */
	private AtomicInteger integer;

	/**
	 * Instantiates a new counter resource.
	 *
	 * @param value the value
	 */
	public CounterResource(int value) {
		integer = new AtomicInteger(value);
	}

	/**
	 * Gets the integer.
	 *
	 * @return the integer
	 */
	public int getInteger() {
		return integer.intValue();
	}

	/**
	 * Sets the integer.
	 *
	 * @param value the new integer
	 */
	public void setInteger(int value) {
		this.integer.set(value);
	}
	
	/**
	 * Increment.
	 *
	 * @return the int
	 */
	public int increment(){
		return integer.incrementAndGet();
	}
}
