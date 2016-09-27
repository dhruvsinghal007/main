package thread;

import dto.FileResource;

// TODO: Auto-generated Javadoc
/**
 * class to write a string to console in thread-safe way. Only one writer can write at a time.
 * @author Dhruv
 *
 */

public class SafeWriter implements Runnable {

	/** The msg. */
	private FileResource msg;
	
	/** The index. */
	private int index;
	
	/**
	 * Instantiates a new safe writer.
	 *
	 * @param msg the msg
	 * @param x the x
	 */
	public SafeWriter(FileResource msg, int x) {
		this.msg = msg;
		index = x;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1 ; i <= 5 ; i++){
			msg.setMessage(""+index);
			try {
				Thread.sleep((long) (Math.random() * 5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

}
