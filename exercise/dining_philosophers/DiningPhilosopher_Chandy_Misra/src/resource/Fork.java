package resource;

/**
 * class describes the possible functionalities of a fork. 
 * @author Dhruv
 *
 */
public class Fork {
	private int id;
	private ForkState state;
	private int philosopherId;
	
	public Fork(int id, ForkState state, int philosopherId) {
		this.id = id;
		this.state = state;
		this.philosopherId = philosopherId;
	}
	public int getPhilosopherId() {
		return philosopherId;
	}
	public void setPhilosopherId(int philosopherId) {
		this.philosopherId = philosopherId;
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
