import java.util.concurrent.Callable;

/**
 * A simple callable thread to increase a number to 10 and finally return it.
 * @author Dhruv
 *
 */
public class Worker implements Callable<Integer> {
	
	@Override
	public Integer call() throws Exception {
		int i = 0;
		while(i < 10){
			System.out.println("Incremented : " + i);
			Thread.sleep(1000);
			i++;
		}
		return i;
	}

}
