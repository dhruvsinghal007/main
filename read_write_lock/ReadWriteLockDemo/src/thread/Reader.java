package thread;

import resource.FileResource;

public class Reader implements Runnable {
	
	private FileResource fileResource;

	public Reader(FileResource msg) {
		this.fileResource = msg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1 ; i <= 5 ; i++){
			System.out.println("Reading : " + fileResource.getMessage() + " by: " + Thread.currentThread().getName());
		}
	}

}
