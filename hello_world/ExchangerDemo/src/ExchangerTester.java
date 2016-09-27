import java.util.concurrent.Exchanger;


// TODO: Auto-generated Javadoc
/**
 * The Class ExchangerTester.
 */
public class ExchangerTester {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();
		new Thread(new Thread1(exchanger, 0),"Thread1").start();
		new Thread(new Thread2(exchanger, 10),"Thread2").start();
		
	}
}
