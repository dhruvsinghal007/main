package tester;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import resource.Fork;
import resource.ForkState;
import resource.Waiter;
import thread.Philosopher;

public class DiningPhilosopherTester {
	public static void main(String[] args) {
		
		Fork[] forks = {
			new Fork(0, ForkState.AVAILABLE),
			new Fork(1, ForkState.AVAILABLE),
			new Fork(2, ForkState.AVAILABLE),
			new Fork(3, ForkState.AVAILABLE),
			new Fork(4, ForkState.AVAILABLE)
		};
		
		Semaphore semaphore = new Semaphore(1);
		CountDownLatch latch = new CountDownLatch(1);
		Waiter waiter = new Waiter(forks, semaphore, latch);
		Philosopher[] philosophers = new Philosopher[]{
			new Philosopher(0, waiter, latch),
			new Philosopher(1, waiter, latch),
			new Philosopher(2, waiter, latch),
			new Philosopher(3, waiter, latch),
			new Philosopher(4, waiter, latch)
		};
		for(int i = 0 ; i < philosophers.length ; i++){
			new Thread(philosophers[i]).start();
		}
	}
}
