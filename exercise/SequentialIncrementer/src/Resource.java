/**
 * although thread safety is done in threads, still n is made volatile if anyhow it gets 
 * modified unexpectedly, the change would immediately be appeared in other threads too.
 * @author Dhruv
 */
public class Resource {
	private volatile int n;
	private String threadId;
	
	public Resource(int n) {
		this.n = n;
		this.setThreadId("t1");
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	
}