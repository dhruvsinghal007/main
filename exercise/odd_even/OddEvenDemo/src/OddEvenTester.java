import java.util.concurrent.atomic.AtomicInteger;


public class OddEvenTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtomicInteger integer = new AtomicInteger(0);
		
		new Thread(new OddThread(integer)).start();
		new Thread(new EvenThread(integer)).start();
		
	}

}
