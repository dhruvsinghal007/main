package integer;

// TODO: Auto-generated Javadoc
/**
 * The Class AtomicIntegerTester.
 */
public class AtomicIntegerTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CounterResource resource = new CounterResource(0);
		for(int i = 0 ; i < 5 ; i++){
			new Thread(new Worker(resource)).start();
		}
	}
}
