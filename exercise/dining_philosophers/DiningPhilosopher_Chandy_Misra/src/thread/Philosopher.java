package thread;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import resource.Fork;
import resource.ForkState;

/**
 * to create a thread for each philosopher, who talks to either of left or right or both 
 * philosophers, and those philosophers will conditionally hand over the shared fork.
 * @author Dhruv
 *
 */
public class Philosopher implements Runnable{

	private int id;
	private Philosopher leftPhilosopher,rightPhilosopher;
	private Fork leftFork,rightFork;
	static final Logger logger = Logger.getLogger(Philosopher.class);
	
	public Philosopher(int id) {
		this.id = id;
		URL u = Philosopher.class.getClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(u);
	}
	
	@Override
	public void run() {
		while(true){
			think();
			requestForks();
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setForks(Fork lFork, Fork rFork) {
		leftFork = lFork;
		rightFork = rFork;
	}
	
	public void setPhilosophers(Philosopher lPhilosopher, Philosopher rPhilosopher) {
		leftPhilosopher = lPhilosopher;
		rightPhilosopher = rPhilosopher;
	}
	
	private void think(){
		//System.out.print("\nThinking : " + Thread.currentThread().getName());
		logger.info("\nThinking : " + Thread.currentThread().getName());
		try {
			Thread.sleep((long) (Math.random() * 10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
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
