package thread;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import resource.Fork;
import resource.ForkState;

// TODO: Auto-generated Javadoc
/**
 * Class Philosopher
 * Create a thread for each philosopher, who talks to either of left or right or both philosophers, and those philosophers will conditionally hand over the shared fork.
 * @author Dhruv
 *
 */
public class Philosopher implements Runnable{

	/** The id. */
	private int id;
	
	/** The right philosopher. */
	private Philosopher leftPhilosopher,rightPhilosopher;
	
	/** The right fork. */
	private Fork leftFork,rightFork;
	
	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(Philosopher.class);
	
	/**
	 * Instantiates a new philosopher.
	 *
	 * @param id the id
	 */
	public Philosopher(int id) {
		this.id = id;
		URL u = Philosopher.class.getClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(u);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true){
			think();
			requestForks();
		}
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
	 * Sets the forks.
	 *
	 * @param lFork the left fork
	 * @param rFork the right fork
	 */
	public void setForks(Fork lFork, Fork rFork) {
		leftFork = lFork;
		rightFork = rFork;
	}
	
	/**
	 * Sets the philosophers.
	 *
	 * @param lPhilosopher the left philosopher
	 * @param rPhilosopher the right philosopher
	 */
	public void setPhilosophers(Philosopher lPhilosopher, Philosopher rPhilosopher) {
		leftPhilosopher = lPhilosopher;
		rightPhilosopher = rPhilosopher;
	}
	
	/**
	 * Think logging
	 */
	private void think(){
		//System.out.print("\nThinking : " + Thread.currentThread().getName());
		logger.info("\nThinking : " + Thread.currentThread().getName());
		try {
			Thread.sleep((long) (Math.random() * 10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eat when both forks are clean. (That's because it comes after being updated to clean from neighbor philosopher)
	 */
	private void eat(){
		synchronized (leftFork) {
			synchronized (rightFork) {
				//System.out.print("\nEating : " + Thread.currentThread().getName());
				logger.info("\nEating : " + Thread.currentThread().getName());
				
				leftFork.setState(ForkState.DIRTY);
				rightFork.setState(ForkState.DIRTY);
				try {
					Thread.sleep((long) (Math.random() * 10000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//System.out.print("\nDone Eating : " + Thread.currentThread().getName());
				logger.info("\nDone Eating : " + Thread.currentThread().getName());
			}
		}
	}
	
	/**
	 * Request forks if the philosopher's id of fork is not equal to current philosopher id. If both id's are equal then eat.
	 */
	private void requestForks(){
		synchronized (leftFork) {
			if(leftFork.getPhilosopherId() != id){
				leftPhilosopher.giveForks(this);
			}
		}
		synchronized (rightFork) {
			if(rightFork.getPhilosopherId() != id){
				rightPhilosopher.giveForks(this);
			}
		}
		if(leftFork.getPhilosopherId() == id && rightFork.getPhilosopherId() == id){
			eat();
		}
	}
	
	/**
	 * Give forks to neighbor. If fork requested is dirty, then make it clean and pass it to neighbor, otherwise eat.
	 *
	 * @param philosopher the philosopher
	 */
	private void giveForks(Philosopher philosopher){
		if(leftPhilosopher.getId() == philosopher.getId()){
			if(leftFork.getState() == ForkState.DIRTY){
				leftFork.setState(ForkState.CLEAN);
				leftFork.setPhilosopherId(philosopher.getId());
			}
		}
		if(rightPhilosopher.getId() == philosopher.getId()){
			if(rightFork.getState() == ForkState.DIRTY){
				rightFork.setState(ForkState.CLEAN);
				rightFork.setPhilosopherId(philosopher.getId());
			}
		}
	}	
}
