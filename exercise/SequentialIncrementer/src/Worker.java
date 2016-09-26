/**
 * Just one runnable to create as many threads as required.
 * @author Dhruv
 */
public class Worker implements Runnable {

	private Resource resource;
	private String nexttThreadId, currentThreadId;
	
	public Worker(String currentThreadId, Resource resource, String nexttThreadId) {
		this.currentThreadId = currentThreadId;
		this.resource = resource;
		this.nexttThreadId = nexttThreadId;
	}

	public String getCurrentThreadId() {
		return currentThreadId;
	}

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
