import java.util.concurrent.Semaphore;

public class OddEvenTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Resource integer = new Resource(0);
		Semaphore lock = new Semaphore(1);
		
		new Thread(new OddThread(integer, lock)).start();
		new Thread(new EvenThread(integer, lock)).start();
		
	}

}
