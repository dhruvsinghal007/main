package delayqueue;

import java.util.concurrent.BlockingQueue;

public class DelayObjectProducer implements Runnable {
	
	private BlockingQueue<DelayObject> queue;

	public DelayObjectProducer(BlockingQueue<DelayObject> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true){
			DelayObject obj = new DelayObject((long) (Math.random() * 5000), "Object" + i++);
			System.out.println("Added : " + obj);
			try {
				queue.put(obj);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
