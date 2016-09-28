package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.safe.ImmutableClass;
import com.safe.MutableClass;

// TODO: Auto-generated Javadoc
/**
 * Class ImmutableTest
 * The immutable class defined has three members : int , String and MutableClass obj. Out of these, only mutable object needs to be tested because others would not have changed anyway.
 * 
 * This class is intended to test if immutable object member of immutable class can still be changed or not.
 */
public class ImmutableTest {

	/**
	 * Test.
	 */
	/*
	 * testing only the mutable member of class
	 */
	@Test
	public void test() {
		ImmutableClass testClass = new ImmutableClass(0, "My string", 
													new MutableClass(0));
		
		String originalId = testClass.getmClassId();
		String newId = testClass.getMClass().toString();
		
		assertEquals(originalId, newId);
	}

}
