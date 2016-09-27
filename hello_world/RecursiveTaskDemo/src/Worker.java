import java.util.concurrent.RecursiveTask;

// TODO: Auto-generated Javadoc
/**
 * simple class to print summation of given range of numbers. If the range has more than 10 elements they are 
 * split into two halves and final result is their combination.
 * @author Dhruv
 *
 */
public class Worker extends RecursiveTask<Integer>{

	/** The start. */
	private int start;
	
	/** The end. */
	private int end;

	/**
	 * Instantiates a new worker.
	 *
	 * @param start the start
	 * @param end the end
	 */
	public Worker(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.RecursiveTask#compute()
	 */
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		if((end-start) <= 10){
			System.out.println("Range : [" + start + " , " + end + "]");
			return computeSum();
		}
		else{
			final Worker firstHalf = new Worker(0 , end/2);
			firstHalf.fork();
			final Worker secondHalf = new Worker((end/2)+1 , end);
			int finalSum = secondHalf.compute() + firstHalf.join();
			System.out.println("Final sum = " + finalSum);
			return finalSum;
		}
	}
	
	/**
	 * Compute sum.
	 *
	 * @return the int
	 */
	private int computeSum(){
		int sum = 0;
		for(int i = start ; i <= end ; i++){
			sum += i;
		}
		System.out.println("Computed sum : " + sum);
		return sum;
	}

}
