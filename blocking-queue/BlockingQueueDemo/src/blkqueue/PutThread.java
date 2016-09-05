package blkqueue;

import java.util.concurrent.BlockingQueue;

public class PutThread implements Runnable{

	BlockingQueue<Integer> bQueue;
	
	public PutThread(BlockingQueue<Integer> bq) {
		// TODO Auto-generated constructor stub
		bQueue = bq;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		int i = 1;
		while(i > 0){
			try {
				bQueue.put(i);
				System.out.println("Element added : " + i);
				Object[] arr = bQueue.toArray();
				
				for(Object ele : arr){
					System.out.print((Integer)ele + " ");
				}
				System.out.println();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	
}
