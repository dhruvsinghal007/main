package resource;

// TODO: Auto-generated Javadoc
/**
 * Class Fork
 * Class describes the possible functionalities of a fork. A fork has a unique id, non-unique states and getters and setter for state.
 * @author Dhruv
 *
 */
public class Fork {
	
	/** The id. */
	private int id;
	
	/** The state. */
	private ForkState state;
	
	/**
	 * Instantiates a new fork.
	 *
	 * @param id the id
	 * @param state the state
	 */
	public Fork(int id, ForkState state) {
		this.id = id;
		this.state = state;
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
