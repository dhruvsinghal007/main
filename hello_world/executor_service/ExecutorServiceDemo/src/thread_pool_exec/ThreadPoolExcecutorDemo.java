package thread_pool_exec;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExcecutorDemo {

	/**
	 * create 10 threads which print numbers from 1 onwards after 10 seconds each.
	 */
	public static void main(String[] args) {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
		
		int j = 1;
		while (j < 11) {
			final String tMsg = "w_" + j;
			executor.submit((new Callable<String>() {
				
				public String call() {
					int i = 1;
					while(true){
						System.out.println(tMsg + "_m" + i++);
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}					
				}
			}));
			j++;			
		}
	}
}
