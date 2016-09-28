package com.single;

// TODO: Auto-generated Javadoc
/**
 * The Class SingletonTester.
 * Attempts to create two objects of singleton. Since the constructor of SingletonClass is private, so static factory method is used to return the instance. So in the end there are two references referring to single object. 
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
