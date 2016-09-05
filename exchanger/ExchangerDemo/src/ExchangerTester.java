import java.util.concurrent.Exchanger;


public class ExchangerTester {
	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();
		new Thread(new Thread1(exchanger, 0),"Thread1").start();
		new Thread(new Thread2(exchanger, 10),"Thread2").start();
		
	}
}
