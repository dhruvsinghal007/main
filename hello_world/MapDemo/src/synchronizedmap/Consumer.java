/**
 * 
 */
package synchronizedmap;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * class to iterate over map.
 *
 * @author Dhruv
 */
public class Consumer implements Runnable {

	/** The map. */
	private Map<Integer, String> map ;	
	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param map the map
	 */
	public Consumer(Map<Integer, String> map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
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
