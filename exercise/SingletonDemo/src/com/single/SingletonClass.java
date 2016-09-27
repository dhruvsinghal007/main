package com.single;

// TODO: Auto-generated Javadoc
/**
 * The Class SingletonClass.
 */
public class SingletonClass {
	
	/** The obj. */
	private static volatile SingletonClass obj ;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new singleton class.
	 *
	 * @param message the message
	 */
	private SingletonClass(String message){
		this.message = message;
	}
	
	/**
	 * Gets the singleton object.
	 *
	 * @param newMessage the new message
	 * @return the singleton object
	 */
	/*
	 * create a new instance of class if not instantiated already with a new string
	 * else return the already instantiated object. Guarantees to return only one
	 * instance.
	 */
	public static SingletonClass getSingletonObject(String newMessage){
		if(obj == null){
			synchronized (SingletonClass.class) {
				if(obj == null){
					obj = new SingletonClass(newMessage);
					System.out.println("New instance created.");
				}
			}
		}
		else{
			System.out.println("New instance not created.");
		}
		return obj;
	}
	
	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString(){
		return message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + message + "]";
	}
	
}
