package volume;

/**
 * contains three fields and equals and hashCode method are overridden over these
 * fields.
 * @author Dhruv
 *
 */
public class Cuboid {
	private int length;
	private int width;
	private int height;
	
	public Cuboid(int length, int width, int height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

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
