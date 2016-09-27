package integer;

// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 */
public class Worker implements Runnable {

	/** The resource. */
	private CounterResource resource;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param resource the resource
	 */
	public Worker(CounterResource resource) {
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i < 5){
			System.out.println("After increment : " + resource.increment() + " by : " + Thread.currentThread().getName());
			try {
				Thread.sleep((long) (Math.random()*5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++ ;
		}
	}

}
