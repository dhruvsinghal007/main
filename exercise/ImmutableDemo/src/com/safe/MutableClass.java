package com.safe;

// TODO: Auto-generated Javadoc
/**
 * Class MutableClass
 * normal class which can be modified.
 *
 */
public class MutableClass {
	
	/** The integer. */
	private int integer;

	/**
	 * Instantiates a new mutable class.
	 *
	 * @param integer the integer
	 */
	public MutableClass(int integer) {
		this.setInteger(integer);
	}

	/**
	 * Gets the integer.
	 *
	 * @return the integer
	 */
	public int getInteger() {
		return integer;
	}

	/**
	 * Sets the integer.
	 *
	 * @param integer the new integer
	 */
	public void setInteger(int integer) {
		this.integer = integer;
	}
	
}
