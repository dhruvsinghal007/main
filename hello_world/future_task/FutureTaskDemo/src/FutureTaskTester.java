import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class FutureTaskTester {

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
