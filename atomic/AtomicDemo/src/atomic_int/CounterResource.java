package atomic_int;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterResource {
	private AtomicInteger integer;

	public CounterResource(int value) {
		integer = new AtomicInteger(value);
	}

	public int getInteger() {
		return integer.intValue();
	}

	public void setInteger(int value) {
		this.integer.set(value);
	}
	
	public int increment(){
		return integer.incrementAndGet();
	}
}
