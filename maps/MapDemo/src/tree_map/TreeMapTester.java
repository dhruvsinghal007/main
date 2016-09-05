package tree_map;

import java.util.TreeMap;

public class TreeMapTester {

	public static void main(String[] args) {
		TreeMapImpl test = new  TreeMapImpl(new TreeMap<String, Integer>());
		
		test.addKeyValuePair("ONE", 1);
		test.addKeyValuePair("TWO", 2);
		test.addKeyValuePair("THREE", 3);
		test.addKeyValuePair("FOUR", 4);
		
		test.printMapKeys();
	}

}
