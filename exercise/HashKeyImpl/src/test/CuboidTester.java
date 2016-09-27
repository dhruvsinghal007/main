package test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import volume.Cuboid;

// TODO: Auto-generated Javadoc
/**
 * Class CuboidTester
 * testing the correctness of equals and hashCode method override. Firstly similar and dissimilar objects are tested for equals, and then the validity of hashCode is tested against a collection storing the cuboid objects for checking availability. 
 * @author Dhruv
 *
 */
public class CuboidTester {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		Cuboid c1 = new Cuboid(2, 5, 8);
		Cuboid c2 = new Cuboid(2, 5, 8);
		
		assertTrue(c1.equals(c2));
		
		Cuboid c3 = new Cuboid(3, 5, 8);
		Cuboid c4 = new Cuboid(2, 6, 8);
		
		assertFalse(c3.equals(c4));
		
		List<Cuboid> list = new LinkedList<>();
		list.add(c1);
		
		assertTrue(list.contains(new Cuboid(2, 5, 8)));
		assertFalse(list.contains(new Cuboid(2, 6, 8)));
		
	}

}
