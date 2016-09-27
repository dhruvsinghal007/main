package hashmap;

import java.util.Map;
import java.util.Map.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class HashMapImpl.
 */
public class HashMapImpl {
	
	/** The map. */
	private Map<String, String> map;
	
	/**
	 * Instantiates a new hash map impl.
	 *
	 * @param map the map
	 */
	public HashMapImpl(Map<String, String> map) {
		this.map = map;
	}

	/**
	 * Adds the key value pair.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addKeyValuePair(String key, String value){
		map.put(key, value);
		System.out.println("\nAdded : [" + key + " , " + value + "]");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String str = "";
		for(Entry<String, String> entry : map.entrySet()){
			str = str + "[" + entry.getKey() + " , " + entry.getValue() + "] ";
		}
		return str;
	}
}
