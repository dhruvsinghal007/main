import java.util.concurrent.Callable;


public class Worker implements Callable<Integer> {

	private int data;
	
	public Worker(int data) {
		this.data = data;
	}
	
	public int getData() {
		return data;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return data++;
	}

}
