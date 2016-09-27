package concurrenthashmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class ConcurrentHashMapTester.
 */
public class ConcurrentHashMapTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
		
		new Thread(new Producer(map)).start();
		new Thread(new Consumer(map)).start();
	}

}
