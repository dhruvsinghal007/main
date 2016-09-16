package com.safe;

/**
 * normal class which can be modified.
 * @author User
 *
 */
public class MutableClass {
	private int integer;

	public MutableClass(int integer) {
		this.setInteger(integer);
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}
	
}
