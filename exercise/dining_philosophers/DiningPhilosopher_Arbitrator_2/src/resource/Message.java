package resource;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message {
	
	/** The id. */
	private String id;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new message.
	 *
	 * @param id the id
	 * @param message the message
	 */
	public Message(String id, String message) {
		this.id = id;
		this.message = message;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
