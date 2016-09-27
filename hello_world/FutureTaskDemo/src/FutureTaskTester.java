import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


// TODO: Auto-generated Javadoc
/**
 * The Class FutureTaskTester.
 */
public class FutureTaskTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FutureTask<Integer> futureTask = new FutureTask<>(new Worker());
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
		
		try {
			System.out.println("Final value returned : " + futureTask.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
