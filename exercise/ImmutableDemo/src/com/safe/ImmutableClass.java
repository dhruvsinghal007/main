package com.safe;

// TODO: Auto-generated Javadoc
/**
 * Immutable class which can not be modified, contains an int, a String and an object
 * of MutableClass.
 *
 * @author User
 */
public final class ImmutableClass {
	
	/** The number. */
	private final int number;
	
	/** The m class id. */
	private final String string, mClassId;
	
	/** The m class. */
	private final MutableClass mClass;
	
	/**
	 * Instantiates a new immutable class.
	 *
	 * @param number the number
	 * @param string the string
	 * @param mClass the m class
	 */
	public ImmutableClass(int number, String string, MutableClass mClass) {
		this.number = number;
		this.string = string;
		this.mClass = mClass;
		mClassId = mClass.toString();
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString(){
		return string;
	}

	/**
	 * Gets the m class id.
	 *
	 * @return the m class id
	 */
	public String getmClassId() {
		return mClassId;
	}

	/**
	 * Gets the m class.
	 *
	 * @return the m class
	 */
	/*
	 * tricky one... since the reference is of mutable class, it should not be returned
	 * rather a new object must be created and returned.
	 */
	public MutableClass getMClass() {
		return new MutableClass(mClass.getInteger());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Number : " + number + ", String : " + string + 
				", MutableClass object : [" + mClass.getInteger() + "]";
	}
}
