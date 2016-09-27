/**
 * 
 */
package concurrentnavigable;

import java.util.NavigableMap;

// TODO: Auto-generated Javadoc
/**
 * class to iterate over map.
 *
 * @author Dhruv
 */
public class Consumer implements Runnable {

	/** The map. */
	private NavigableMap<Integer, String> map ;	
	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param map the map
	 */
	public Consumer(NavigableMap<Integer, String> map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 10 ; i++){
			System.out.println("Printing elements after 2");
			NavigableMap<Integer, String> map = (NavigableMap<Integer, String>) this.map.tailMap(2,false);
			
			for(Integer key : map.keySet()){
				System.out.print("(" + key +", " + map.get(key)+ ") ");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
