package resource.unsafe;

public class Resource {
	private volatile int number;
	
	public Resource(int number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(int integer) {
		this.number = integer;
	}
}
