package synchronizedmap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class SynchronizedMapTester.
 */
public class SynchronizedMapTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, String> map = Collections.synchronizedMap(new HashMap<Integer, String>());
		
		new Thread(new Producer(map)).start();
		new Thread(new Consumer(map)).start();
			
	}

}
