package atomic_int;

public class Worker implements Runnable {

	private CounterResource resource;
	
	public Worker(CounterResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i < 5){
			System.out.println("After increment : " + resource.increment() + " by : " + Thread.currentThread().getName());
			try {
				Thread.sleep((long) (Math.random()*5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++ ;
		}
	}

}
