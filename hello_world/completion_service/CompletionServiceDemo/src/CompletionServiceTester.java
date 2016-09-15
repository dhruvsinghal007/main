import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;


public class CompletionServiceTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Executor ex = Executors.newScheduledThreadPool(5);
		CompletionService<Integer> service = new ExecutorCompletionService<>(ex);
		new Thread(new Producer(service)).start();
		new Thread(new Consumer(service)).start();
	}

}
