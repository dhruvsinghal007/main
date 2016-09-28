
// TODO: Auto-generated Javadoc
/**
 * The Class SequentialIncremental.
 * Just createsd three threads. Can create as many as desired. Just set the philosopher id's in circular fashion.
 */
public class SequentialIncremental {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource resource = new Resource(0);
		Worker thread1 = new Worker("t1", resource, "t2");
		Worker thread2 = new Worker("t2", resource, "t3");
		Worker thread3 = new Worker("t3", resource, "t1");
		
		new Thread(thread1, thread1.getCurrentThreadId()).start();
		new Thread(thread2, thread2.getCurrentThreadId()).start();
		new Thread(thread3, thread3.getCurrentThreadId()).start();
	}

}
