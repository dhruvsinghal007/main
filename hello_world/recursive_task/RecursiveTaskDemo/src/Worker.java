import java.util.concurrent.RecursiveTask;

/**
 * simple class to print summation of given range of numbers. If the range has more than 10 elements they are 
 * split into two halves and final result is their combination.
 * @author Dhruv
 *
 */
public class Worker extends RecursiveTask<Integer>{

	private int start;
	private int end;

	public Worker(int start, int end) {
		this.start = start;
		this.end = end;
	}

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
	
	private int computeSum(){
		int sum = 0;
		for(int i = start ; i <= end ; i++){
			sum += i;
		}
		System.out.println("Computed sum : " + sum);
		return sum;
	}

}
