package tester;

import resource.FileResource;
import thread.Reader;
import thread.Writer;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadWriteLockTester.
 */
public class ReadWriteLockTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileResource file = new FileResource("Message");
		for(int i = 1 ; i <= 5 ; i++){
			new Thread(new Reader(file)).start();
			new Thread(new Writer(file, i)).start();
		}
	}

}
