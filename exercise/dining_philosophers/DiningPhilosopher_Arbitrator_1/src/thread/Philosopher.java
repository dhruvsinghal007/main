package thread;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import resource.Fork;
import resource.Waiter;

/**
 * to create a thread for each philosopher, each one of them requests waiter for forks, if available
 * eat and return forks.
 * @author Dhruv
 *
 */
public class Philosopher implements Runnable{

	private int id;
	private Waiter waiter;
	private List<Fork> forks;
	private CountDownLatch latch;
	static final Logger logger = Logger.getLogger(Philosopher.class);

	public Philosopher(int id, Waiter waiter, CountDownLatch latch) {
		this.id = id;
		this.waiter = waiter;
		this.latch = latch;
		URL u = Philosopher.class.getClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(u);
	}
	
	public void setForks(List<Fork> forks) {
		this.forks = forks;
	}
	
	public int getId() {
		return id;
	}
	
	private void think(){
		//System.out.print("\nThread " + id + " thinking...");
		logger.info("\nThread " + id + " thinking...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean eat(){
		if(forks == null || forks.size() < 2){
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			//System.out.print("\nEating : Thread " + id);
			logger.info("\nEating : Thread " + id);
			try {
				Thread.sleep((long) (Math.random() * 10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	private void releaseForks(boolean flag){
		if(flag){
			//System.out.print("\nThread " + id + " released forks");
			logger.info("\nThread " + id + " released forks");
			waiter.takeForks(forks);
			forks = null;
		}
	}
	
	@Override
	public void run() {
		while(true){
			think();
			waiter.addRequest(this);
			boolean fl = eat();
			releaseForks(fl);
		}
	}

}
