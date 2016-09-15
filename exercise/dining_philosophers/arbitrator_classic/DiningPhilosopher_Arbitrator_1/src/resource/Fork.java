package resource;

/**
 * class describes the possible functionalities of a fork. 
 * @author Dhruv
 *
 */
public class Fork {
	private int id;
	private ForkState state;
	
	public Fork(int id, ForkState state) {
		this.id = id;
		this.state = state;
	}
	public ForkState getState() {
		return state;
	}
	public void setState(ForkState state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
}
