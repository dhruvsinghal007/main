package resource;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import thread.Philosopher;

// TODO: Auto-generated Javadoc
/**
 * Class Waiter
 * waiter keeps the lock and does synchronization on every request, updates the fork states
 * after each request if successful and interacts with philosopher. Only one at a time is allowed
 * to eat
 * @author Dhruv
 *
 */
public class Waiter{
	
	/** The forks. */
	private Fork[] forks;
	
	/** The semaphore. */
	private Semaphore semaphore;
	
	/** The philosopher queue. */
	private BlockingQueue<Philosopher> queue;
	
	/** The latch. */
	private CountDownLatch latch;
	
	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(Waiter.class);
	
	/**
	 * Instantiates a new waiter.
	 *
	 * @param forks the forks
	 * @param semaphore the semaphore
	 * @param latch the latch
	 */
	public Waiter(Fork[] forks, Semaphore semaphore, CountDownLatch latch) {
		// TODO Auto-generated constructor stub
		this.forks = forks;
		this.semaphore = semaphore;
		queue = new LinkedBlockingQueue<Philosopher>();
		this.latch = latch;
		URL u = Waiter.class.getClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(u);
	}
	
	/**
	 * Adds the request.
	 *
	 * @param philosopher the philosopher
	 */
	public void addRequest(Philosopher philosopher){
		try {
			queue.put(philosopher);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		giveForks();
	}
	
	/**
	 * Give forks to front philosopher in queue.
	 */
	private void giveForks(){
		try {
			semaphore.acquire();
			//System.out.print("\nPhilosopher Queue status : ");
			logger.info("\nPhilosopher Queue status : ");
			for(Philosopher ph : queue){
				System.out.print(ph.getId() + " ");
			}
			int id = queue.peek().getId();
			List<Fork> forkList = new ArrayList<>();
			
			if((forks[id].getState() == ForkState.AVAILABLE)&&
					(forks[(id + 1) % 5].getState() == ForkState.AVAILABLE)){
				
				for(int index = id ; index <= id + 1 ; index++){
					forks[index % 5].setState(ForkState.NOT_AVAILABLE);
					forkList.add(forks[index % 5]);
				}
			}
			if(forkList.size() == 2){
				queue.take().setForks(forkList);
				latch.countDown();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Take forks from philosopher that finishes eating.
	 *
	 * @param list the list
	 */
	public void takeForks(List<Fork> list){
		for(Fork fork : list){
			for(int i = 0 ; i < forks.length ; i++){
				if(forks[i].getId() == fork.getId()){
					forks[i].setState(ForkState.AVAILABLE);
				}
			}
		}
		semaphore.release();
		latch.countDown();
	}
}
