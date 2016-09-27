package thread;

import resource.FileResource;

// TODO: Auto-generated Javadoc
/**
 * The Class Reader.
 */
public class Reader implements Runnable {
	
	/** The file resource. */
	private FileResource fileResource;

	/**
	 * Instantiates a new reader.
	 *
	 * @param msg the msg
	 */
	public Reader(FileResource msg) {
		this.fileResource = msg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1 ; i <= 5 ; i++){
			System.out.println("Reading : " + fileResource.getMessage() + " by: " + Thread.currentThread().getName());
		}
	}

}
