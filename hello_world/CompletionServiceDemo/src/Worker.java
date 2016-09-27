import java.util.concurrent.Callable;


// TODO: Auto-generated Javadoc
/**
 * The Class Worker.
 * implements the working of completion service -- adding services and providing the completed ones
 */
public class Worker implements Callable<Integer> {

	/** The data. */
	private int data;
	
	/**
	 * Instantiates a new worker.
	 *
	 * @param data the data
	 */
	public Worker(int data) {
		this.data = data;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return data++;
	}

}
