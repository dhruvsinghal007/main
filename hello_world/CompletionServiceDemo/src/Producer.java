import java.util.concurrent.CompletionService;


// TODO: Auto-generated Javadoc
/**
 * The Class Producer.
 * adds new tasks into completion service.
 */
public class Producer implements Runnable {
	
	/** The service. */
	private CompletionService<Integer> service;

	/**
	 * Instantiates a new producer.
	 *
	 * @param service the completion service
	 */
	public Producer(CompletionService<Integer> service) {
		this.service = service;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true){
			Worker worker = new Worker(i++);
			System.out.println("Result added : " + worker.getData());
			service.submit(worker);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
