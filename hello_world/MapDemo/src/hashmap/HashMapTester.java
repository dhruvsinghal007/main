package hashmap;

import java.util.LinkedHashMap;

public class HashMapTester {

	public static void main(String[] args) {
		HashMapImpl test = new  HashMapImpl(new LinkedHashMap<String, String>());
		
		test.addKeyValuePair("ONE", "1");
		test.addKeyValuePair("TWO", "2");
		test.addKeyValuePair("THREE", "3");
		test.addKeyValuePair("FOUR", "4");
		
		System.out.println("\nMap entries : " + test);
		
		test.addKeyValuePair("TWO", "02");
		test.addKeyValuePair("THREE", "03"); //just checking what happens...
		
		System.out.println("\nMap entries : " + test);
		
	}

}
