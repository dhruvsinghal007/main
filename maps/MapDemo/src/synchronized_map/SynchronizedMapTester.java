package synchronized_map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, String> map = Collections.synchronizedMap(new HashMap<Integer, String>());
		
		new Thread(new Producer(map)).start();
		new Thread(new Consumer(map)).start();
			
	}

}
