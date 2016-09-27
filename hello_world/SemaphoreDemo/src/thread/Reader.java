package thread;

import dto.FileResource;

// TODO: Auto-generated Javadoc
/**
 * class for reading string from fileresource.
 *
 * @author Dhruv
 */

public class Reader implements Runnable {
	
	/** The msg. */
	private FileResource msg;

	/**
	 * Instantiates a new reader.
	 *
	 * @param msg the msg
	 */
	public Reader(FileResource msg) {
		this.msg = msg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1 ; i <= 5 ; i++){
			msg.printMessage();
		}
	}

}
