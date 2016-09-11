package atomic_int;

public class AtomicIntegerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CounterResource resource = new CounterResource(0);
		for(int i = 0 ; i < 5 ; i++){
			new Thread(new Worker(resource)).start();
		}
	}
}
