
public class OneValueThread implements Runnable {

	private ThreadLocal<Integer> threadLocal;
	
	public OneValueThread(ThreadLocal<Integer> threadLocal) {
		this.threadLocal = threadLocal;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadLocal.set(1);
		System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
	}

}
