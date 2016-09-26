
public class SequentialIncremental {

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
