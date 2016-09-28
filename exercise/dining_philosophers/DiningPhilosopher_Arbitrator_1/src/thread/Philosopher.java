package thread;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import resource.Fork;
import resource.Waiter;

// TODO: Auto-generated Javadoc
/**
 * Class Philosopher
 * Create a thread for each philosopher, each one of them requests waiter for forks, if available eat and return forks.
 * @author Dhruv
 *
 */
public class Philosopher implements Runnable{

	/** The id. */
	private int id;
	
	/** The waiter. */
	private Waiter waiter;
	
	/** The forks. */
	private List<Fork> forks;
	
	/** The latch. */
	private CountDownLatch latch;
	
	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(Philosopher.class);

	/**
	 * Instantiates a new philosopher and DOMConfigurator for logging.
	 *
	 * @param id the id
	 * @param waiter the waiter
	 * @param latch the latch
	 */
	public Philosopher(int id, Waiter waiter, CountDownLatch latch) {
		this.id = id;
		this.waiter = waiter;
		this.latch = latch;
		URL u = Philosopher.class.getClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(u);
	}
	
	/**
	 * Sets the forks.
	 *
	 * @param forks the new forks
	 */
	public void setForks(List<Fork> forks) {
		this.forks = forks;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Think. Just a print statement and then sleep for random time less than 10 sec.
	 */
	private void think(){
		//System.out.print("\nThread " + id + " thinking...");
		logger.info("\nThread " + id + " thinking...");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eat when all forks are available, otherwise wait.
	 *
	 * @return true, if successful
	 */
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
	
	/**
	 * Release forks when done eating.
	 *
	 * @param flag the flag
	 */
	private void releaseForks(boolean flag){
		if(flag){
			//System.out.print("\nThread " + id + " released forks");
			logger.info("\nThread " + id + " released forks");
			waiter.takeForks(forks);
			forks = null;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
