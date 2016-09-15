package delayqueue;

import java.util.concurrent.BlockingQueue;

public class DelayObjectConsumer implements Runnable {
	
	private BlockingQueue<DelayObject> queue;

	public DelayObjectConsumer(BlockingQueue<DelayObject> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				System.out.println("Removed : " + queue.take().getMessage() + " time : " + System.currentTimeMillis());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
