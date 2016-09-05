import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;


public class Consumer implements Runnable {

	private CompletionService<Integer> service;
	
	public Consumer(CompletionService<Integer> service) {
		this.service = service;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				System.out.println("Service fetch : "+service.take().get());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
