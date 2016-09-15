package thread;

import dto.FileResource;

/**
 * class for reading string from fileresource
 * @author Dhruv
 *
 */

public class Reader implements Runnable {
	
	private FileResource msg;

	public Reader(FileResource msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1 ; i <= 5 ; i++){
			msg.printMessage();
		}
	}

}
