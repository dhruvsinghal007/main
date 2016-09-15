package com.safe;

public class ImmutableTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImmutableClass safeObject = new ImmutableClass(1, " Fixed String", 
															new MutableClass(10) );
		System.out.println(safeObject);
		
	}

}
