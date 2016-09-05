package test;

import dto.FileResource;
import thread.Reader;
import thread.SafeWriter;

public class SemaphoreTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileResource file = new dto.FileResource("Message");
		for(int i = 1 ; i <= 5 ; i++){
			new Thread(new Reader(file)).start();
			new Thread(new SafeWriter(file, i)).start();
		}
	}

}
