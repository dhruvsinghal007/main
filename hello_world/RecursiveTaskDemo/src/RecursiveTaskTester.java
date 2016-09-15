import java.util.concurrent.ForkJoinPool;

public class RecursiveTaskTester {
	public static void main(String[] args) {
		int end = (int) (Math.random() * 20);
		System.out.println("Input : " + end);
		Worker worker = new Worker(0, end);
		ForkJoinPool pool = new ForkJoinPool(2);
		pool.invoke(worker);
	}
}
