import java.util.concurrent.CompletionService;


public class Producer implements Runnable {
	
	private CompletionService<Integer> service;

	public Producer(CompletionService<Integer> service) {
		this.service = service;
	}

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
