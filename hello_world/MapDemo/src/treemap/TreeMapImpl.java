package treemap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapImpl {
	private TreeMap<String, Integer> map;
	// sorting tester on strings

	public TreeMapImpl(TreeMap<String, Integer> map) {
		this.map = map;
	}

	public void addKeyValuePair(String key, int value){
		map.put(key, value);
		System.out.println("Added : [" + key + " , " + value + "]");
	}
	
	public void printMapKeys(){
		Set<String> keySet = map.keySet();
		System.out.println("Printing keys in natural ordering...");
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
	}
}
