package batch.thread;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

	private BlockingQueue<String> bQueue;
	
	public BlockingQueue<String> getbQueue() {
		return bQueue;
	}

	public Producer(BlockingQueue<String> bq) {
		// TODO Auto-generated constructor stub
		bQueue = bq;
	}
	
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		int i = 1;
		while(true){
			try {
				String msg = "Message " + i;
				System.out.println("\nString added : " + msg);
				bQueue.put(msg);
				for(String str : bQueue){
					System.out.print(str + " ");
				}
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

}
