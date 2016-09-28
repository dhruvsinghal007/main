package tester;

import resource.Fork;
import resource.ForkState;
import resource.PrintMessage;
import thread.Philosopher;
import thread.Waiter;

// TODO: Auto-generated Javadoc
/**
 * The Class DiningPhilosopherTester.
 * Implements enhanced version of arbitrator solution for dining philosophers problem. Here more than one philosophers are allowed to eat at a time, but here also waiter is interacting with philosophers for dining.
 */
public class DiningPhilosopherTester {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Fork[] forks = {
			new Fork(0, ForkState.AVAILABLE),
			new Fork(1, ForkState.AVAILABLE),
			new Fork(2, ForkState.AVAILABLE),
			new Fork(3, ForkState.AVAILABLE),
			new Fork(4, ForkState.AVAILABLE)
		};
		
		Waiter waiter = new Waiter(forks);
		Philosopher[] philosophers = new Philosopher[]{
			new Philosopher(0, waiter),
			new Philosopher(1, waiter),
			new Philosopher(2, waiter),
			new Philosopher(3, waiter),
			new Philosopher(4, waiter)
		};
			
		for(int i = 0 ; i < philosophers.length ; i++){
			new Thread(philosophers[i]).start();
		}
		new Thread(waiter).start();
		new Thread(new PrintMessage()).start();
	}
}
