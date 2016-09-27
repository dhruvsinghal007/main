package treemap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

// TODO: Auto-generated Javadoc
/**
 * The Class TreeMapImpl.
 */
public class TreeMapImpl {
	
	/** The map. */
	private TreeMap<String, Integer> map;
	// sorting tester on strings

	/**
	 * Instantiates a new tree map impl.
	 *
	 * @param map the map
	 */
	public TreeMapImpl(TreeMap<String, Integer> map) {
		this.map = map;
	}

	/**
	 * Adds the key value pair.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addKeyValuePair(String key, int value){
		map.put(key, value);
		System.out.println("Added : [" + key + " , " + value + "]");
	}
	
	/**
	 * Prints the map keys.
	 */
	public void printMapKeys(){
		Set<String> keySet = map.keySet();
		System.out.println("Printing keys in natural ordering...");
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
	}
}
