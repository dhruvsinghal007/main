package concurrenthashmap;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * class to put elements in map.
 *
 * @author Dhruv
 */
public class Producer implements Runnable {

	/** The map. */
	private Map<Integer, String> map ;
	
	/**
	 * Instantiates a new producer.
	 *
	 * @param map the map
	 */
	public Producer(Map<Integer, String> map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; ; i++){
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
