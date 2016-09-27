package com.single;

// TODO: Auto-generated Javadoc
/**
 * The Class SingletonTester.
 */
public class SingletonTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonClass singleton1 = SingletonClass.getSingletonObject("First Invocation");
		SingletonClass singleton2= SingletonClass.getSingletonObject("Second Invocation");
		
		System.out.println(singleton1 + "\n" + singleton2);
	}

}
