package thread;

import dto.FileResource;

/**
 * class to write a string to console in thread-safe way. Only one writer can write at a time.
 * @author Dhruv
 *
 */

public class SafeWriter implements Runnable {

	private FileResource msg;
	private int index;
	
	public SafeWriter(FileResource msg, int x) {
		this.msg = msg;
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
			i++;
		}
	}

}
