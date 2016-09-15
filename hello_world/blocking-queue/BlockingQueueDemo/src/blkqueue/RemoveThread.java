package blkqueue;

import java.util.concurrent.BlockingQueue;

public class RemoveThread implements Runnable {

	BlockingQueue<Integer> bQueue;
	public RemoveThread(BlockingQueue<Integer> bQueue) {
		// TODO Auto-generated constructor stub
		this.bQueue = bQueue;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Integer res = bQueue.take();
				System.out.println("Element removed : " + res);
				Object[] arr = bQueue.toArray();
				
				for(Object ele : arr){
					System.out.print((Integer)ele + " ");
				}
				System.out.println();
				
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
