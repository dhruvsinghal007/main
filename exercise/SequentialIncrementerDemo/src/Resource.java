// TODO: Auto-generated Javadoc
/**
 * Class Resource
 * Contains a volatile member and threadId
 * @author Dhruv
 */
public class Resource {
	
	/** The volatile member n. */
	private volatile int n;
	
	/** The thread id. */
	private String threadId;
	
	/**
	 * Instantiates a new resource.
	 *
	 * @param n the integer
	 */
	public Resource(int n) {
		this.n = n;
		this.setThreadId("t1");
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * Gets the thread id.
	 *
	 * @return the thread id
	 */
	public String getThreadId() {
		return threadId;
	}

	/**
	 * Sets the thread id.
	 *
	 * @param threadId the new thread id
	 */
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	
}