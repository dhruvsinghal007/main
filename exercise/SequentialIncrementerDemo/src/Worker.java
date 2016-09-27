// TODO: Auto-generated Javadoc
/**
 *Class Worker.
 *Able to create as many threads as desired, to print the integers sequentially
 * 
 * @author Dhruv
 */
public class Worker implements Runnable {

	/** The resource. */
	private Resource resource;
	
	/** The current thread id. */
	private String nexttThreadId, currentThreadId;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param currentThreadId the current thread id
	 * @param resource the resource
	 * @param nexttThreadId the nextt thread id
	 */
	public Worker(String currentThreadId, Resource resource, String nexttThreadId) {
		this.currentThreadId = currentThreadId;
		this.resource = resource;
		this.nexttThreadId = nexttThreadId;
	}

	/**
	 * Gets the current thread id.
	 *
	 * @return the current thread id
	 */
	public String getCurrentThreadId() {
		return currentThreadId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (resource) {
				if(currentThreadId.equals(resource.getThreadId())){
					resource.setN(resource.getN() + 1);
					resource.setThreadId(nexttThreadId);
					System.out.println(Thread.currentThread().getName() + " : " + resource.getN());
					resource.notifyAll();
				}
				else {
					try {
						resource.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
