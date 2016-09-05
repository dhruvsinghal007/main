package delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class DelayQueueTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<DelayObject> queue = new DelayQueue<>();
		
		new Thread(new DelayObjectProducer(queue)).start();
		new Thread(new DelayObjectConsumer(queue)).start();
	}

}
