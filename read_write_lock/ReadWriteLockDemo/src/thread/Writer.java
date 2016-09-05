package thread;

import resource.FileResource;

/**
 * class to write a string to console in thread-safe way. Only one writer can write at a time.
 * @author Dhruv
 *
 */

public class Writer implements Runnable {

	private FileResource msg;
	private int index;
	
	public Writer(FileResource fileResource, int x) {
		this.msg = fileResource;
		index = x;
	}

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
