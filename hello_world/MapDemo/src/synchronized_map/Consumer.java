/**
 * 
 */
package synchronized_map;

import java.util.Map;

/**
 * class to iterate over map
 * @author Dhruv
 *
 */
public class Consumer implements Runnable {

	private Map<Integer, String> map ;	
	public Consumer(Map<Integer, String> map) {
		this.map = map;
	}

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
