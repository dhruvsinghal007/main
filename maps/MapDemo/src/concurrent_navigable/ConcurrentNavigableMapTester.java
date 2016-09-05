package concurrent_navigable;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentNavigableMapTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NavigableMap<Integer, String> map = new ConcurrentSkipListMap<>();
		
		Thread t1 = new Thread(new Producer(map));
		t1.start();
//		try {
//			t1.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		new Thread(new Consumer(map)).start();
	}

}
