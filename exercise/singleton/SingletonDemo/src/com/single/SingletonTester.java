package com.single;

public class SingletonTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonClass singleton1 = SingletonClass.getSingletonObject("First Invocation");
		SingletonClass singleton2= SingletonClass.getSingletonObject("Second Invocation");
		
		System.out.println(singleton1 + "\n" + singleton2);
	}

}
