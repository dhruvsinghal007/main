package tester;

import resource.Fork;
import resource.ForkState;
import thread.Philosopher;

// TODO: Auto-generated Javadoc
/**
 * Class DiningPhilosopherTester
 * Implements Chandy Misra solution for dining philosophers problem. At first all forks are dirty and assigned to the left philosopher. All philosophers have reference to left and right philosophers and forks. If a request comes to a philosopher he gives the fork if the fork if dirty by first updating it to clean.
 * @author Dhruv
 *
 */
public class DiningPhilosopherTester {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Philosopher[] philosophers = new Philosopher[]{
			new Philosopher(0),
			new Philosopher(1),
			new Philosopher(2),
			new Philosopher(3),
			new Philosopher(4)
		};
		
		Fork[] forks = {
				new Fork(0, ForkState.DIRTY, philosophers[0].getId()),
				new Fork(1, ForkState.DIRTY, philosophers[1].getId()),
				new Fork(2, ForkState.DIRTY, philosophers[2].getId()),
				new Fork(3, ForkState.DIRTY, philosophers[3].getId()),
				new Fork(4, ForkState.DIRTY, philosophers[4].getId())
			};
		
		for(int i = 0 ; i < philosophers.length ; i++){
			philosophers[i].setPhilosophers(philosophers[(i + 4) % 5], philosophers[(i + 1) % 5]);
			philosophers[i].setForks(forks[i], forks[(i + 1) % 5]);
		}
		for(int i = 0 ; i < 5 ; i++){
			new Thread(philosophers[i]).start();
		}
	}
}
