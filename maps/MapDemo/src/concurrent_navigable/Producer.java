/**
 * 
 */
package concurrent_navigable;

import java.util.Map;

/**
 * class to put elements in map
 * @author Dhruv
 *
 */
public class Producer implements Runnable {

	private Map<Integer, String> map ;
	
	public Producer(Map<Integer, String> map) {
		this.map = map;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ; i++){
			String msg = "String" + i;
			map.put(i, msg);
			System.out.println("\nAdded : (" + i + ", " + msg + ")");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
