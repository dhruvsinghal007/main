package com.safe;

/**
 * Immutable class which can not be modified, contains an int, a String and an object
 * of MutableClass
 * @author User
 *
 */
public final class ImmutableClass {
	private final int number;
	private final String string, mClassId;
	private final MutableClass mClass;
	
	public ImmutableClass(int number, String string, MutableClass mClass) {
		this.number = number;
		this.string = string;
		this.mClass = mClass;
		mClassId = mClass.toString();
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getString(){
		return string;
	}

	public String getmClassId() {
		return mClassId;
	}

	/*
	 * tricky one... since the reference is of mutable class, it should not be returned
	 * rather a new object must be created and returned.
	 */
	public MutableClass getMClass() {
		return new MutableClass(mClass.getInteger());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Number : " + number + ", String : " + string + 
				", MutableClass object : [" + mClass.getInteger() + "]";
	}
}
