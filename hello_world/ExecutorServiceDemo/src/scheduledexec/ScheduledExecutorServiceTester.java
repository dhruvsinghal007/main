package scheduledexec;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class ScheduledExecutorServiceTester
 * class to print message by 5 threads after a certain interval of time each (seconds)
 */

public class ScheduledExecutorServiceTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledThreadPoolExecutor exe = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
		
		for(int i = 1 ; i <= 5 ; i++){
			final String msg = "th_" + i + "_";
			exe.scheduleWithFixedDelay(new Runnable() {
				
				int i = 1 ;
				@Override
				public synchronized void run() {
					// TODO Auto-generated method stub
					String str = msg + "m_" + i++;
					System.out.println(str);
				}
			}, 0, 1000 * i , TimeUnit.MILLISECONDS);
		}
		
	}

}
