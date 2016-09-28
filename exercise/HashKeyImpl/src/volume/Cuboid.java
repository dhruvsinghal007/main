package volume;

// TODO: Auto-generated Javadoc
/**
 * Class Cuboid
 * contains three fields, and equals and hashCode method are overridden over these fields.
 * @author Dhruv
 *
 */
public class Cuboid {
	
	/** The length. */
	private int length;
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height;
	
	/**
	 * Instantiates a new cuboid.
	 *
	 * @param length the length
	 * @param width the width
	 * @param height the height
	 */
	public Cuboid(int length, int width, int height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if(! (obj instanceof Cuboid)){
			return false;
		}
		else{
			Cuboid cObj = (Cuboid) obj;
			if(cObj.getLength() == length &&
					cObj.getWidth() == width &&
					cObj.getHeight() == height){
				return true;
			}
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		
		int result = 17;
		result = result * 31 + length;
		result = result * 31 + width;
		result = result * 31 + height;
		
		return result;
	}
	
	
}
