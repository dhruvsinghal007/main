package com.safe;

// TODO: Auto-generated Javadoc
/**
 * The Class ImmutableTester.
 * Create an instance of ImmutableClass and display the contents
 */
public class ImmutableTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImmutableClass safeObject = new ImmutableClass(1, " Fixed String", 
															new MutableClass(10) );
		System.out.println(safeObject);
		
	}

}
