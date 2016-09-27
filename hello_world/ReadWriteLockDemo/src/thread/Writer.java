package thread;

import resource.FileResource;

// TODO: Auto-generated Javadoc
/**
 * Class Writer
 * class to write a string to console in thread-safe way. Only one writer can write at a time.
 * @author Dhruv
 *
 */

public class Writer implements Runnable {

	/** The msg. */
	private FileResource msg;
	
	/** The index. */
	private int index;
	
	/**
	 * Instantiates a new writer.
	 *
	 * @param fileResource the file resource
	 * @param x the x
	 */
	public Writer(FileResource fileResource, int x) {
		this.msg = fileResource;
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
		}
	}

}
