
public class ThreeValueThread implements Runnable {

	private ThreadLocal<Integer> threadLocal;
	
	public ThreeValueThread(ThreadLocal<Integer> threadLocal) {
		this.threadLocal = threadLocal;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		threadLocal.set(3);
		System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
	}

}
