import java.util.concurrent.Callable;

// TODO: Auto-generated Javadoc
/**
 * Class Worker
 * A simple callable thread to increase a number to 10 and finally return it.
 * @author Dhruv
 *
 */
public class Worker implements Callable<Integer> {
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
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
