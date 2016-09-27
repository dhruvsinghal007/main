import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;


// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 * fetches the completed task from completion service.
 */
public class Consumer implements Runnable {

	/** The service. */
	private CompletionService<Integer> service;
	
	/**
	 * Instantiates a new consumer.
	 *
	 * @param service the completion service
	 */
	public Consumer(CompletionService<Integer> service) {
		this.service = service;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
