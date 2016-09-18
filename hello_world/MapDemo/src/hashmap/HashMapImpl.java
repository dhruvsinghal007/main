package hashmap;

import java.util.Map;
import java.util.Map.Entry;

public class HashMapImpl {
	private Map<String, String> map;
	
	public HashMapImpl(Map<String, String> map) {
		this.map = map;
	}

	public void addKeyValuePair(String key, String value){
		map.put(key, value);
		System.out.println("\nAdded : [" + key + " , " + value + "]");
	}
	
	public String toString(){
		String str = "";
		for(Entry<String, String> entry : map.entrySet()){
			str = str + "[" + entry.getKey() + " , " + entry.getValue() + "] ";
		}
		return str;
	}
}
