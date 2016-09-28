package thread;

import java.util.List;

import resource.Fork;
import resource.PrintMessage;

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
	
	/**
	 * Instantiates a new philosopher.
	 *
	 * @param id the id
	 * @param waiter the waiter
	 */
	public Philosopher(int id, Waiter waiter) {
		this.id = id;
		this.waiter = waiter;
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
	 * Print statement for Think.
	 */
	private synchronized void think(){
		try {
			long time = (long) (Math.random() * 10000);
			PrintMessage.print("p"+id, " Thinking " + (time/1000) + " sec ");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eat if forks are available, otherwise wait.
	 *
	 * @return true, if successful
	 */
	private synchronized boolean eat(){
		if(forks == null || forks.size() < 2){
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			try {
				long time = (long) (Math.random() * 10000);
				PrintMessage.print("p"+id, " Eating " + (time/1000) + " sec ");
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Release forks only if eating is already done
	 *
	 * @param flag the flag
	 */
	private synchronized void releaseForks(boolean flag){
		if(flag){
			PrintMessage.print("p"+id, " Released forks. ");
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
