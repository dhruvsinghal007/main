package com.single;

public class SingletonClass {
	private static SingletonClass obj ;
	private String message;
	
	private SingletonClass(String message){
		this.message = message;
	}
	
	/*
	 * create a new instance of class if not instantiated already with a new string
	 * else return the already instantiated object. Guarantees to return only one
	 * instance.
	 */
	public static SingletonClass getSingletonObject(String newMessage){
		if(obj == null){
			obj = new SingletonClass(newMessage);
			System.out.println("New instance created.");
		}
		else{
			System.out.println("New instance not created.");
		}
		return obj;
	}
	
	public String getString(){
		return message;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + message + "]";
	}
	
}
