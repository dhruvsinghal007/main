
public class TwoValueThread implements Runnable {

	private ThreadLocal<Integer> threadLocal;
	
	public TwoValueThread(ThreadLocal<Integer> threadLocal) {
		this.threadLocal = threadLocal;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadLocal.set(2);
		System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
	}

}
