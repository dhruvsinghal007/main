package resource;

// TODO: Auto-generated Javadoc
/**
 * Class Fork
 * class describes the possible functionalities of a fork. 
 * @author Dhruv
 *
 */
public class Fork {
	
	/** The id. */
	private int id;
	
	/** The state. */
	private ForkState state;
	
	/** The philosopher id. */
	private int philosopherId;
	
	/**
	 * Instantiates a new fork.
	 *
	 * @param id the id
	 * @param state the state
	 * @param philosopherId the philosopher id
	 */
	public Fork(int id, ForkState state, int philosopherId) {
		this.id = id;
		this.state = state;
		this.philosopherId = philosopherId;
	}
	
	/**
	 * Gets the philosopher id.
	 *
	 * @return the philosopher id
	 */
	public int getPhilosopherId() {
		return philosopherId;
	}
	
	/**
	 * Sets the philosopher id.
	 *
	 * @param philosopherId the new philosopher id
	 */
	public void setPhilosopherId(int philosopherId) {
		this.philosopherId = philosopherId;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public ForkState getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(ForkState state) {
		this.state = state;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
