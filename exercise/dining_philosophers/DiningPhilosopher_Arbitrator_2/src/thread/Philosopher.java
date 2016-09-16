package thread;

import java.util.List;

import resource.Fork;
import resource.PrintMessage;

/**
 * to create a thread for each philosopher, each one of them requests waiter for forks, if available
 * eat and return forks.
 * @author Dhruv
 *
 */
public class Philosopher implements Runnable{

	private int id;
	private Waiter waiter;
	
	public void setForks(List<Fork> forks) {
		this.forks = forks;
	}

	private List<Fork> forks;
	
	public Philosopher(int id, Waiter waiter) {
		this.id = id;
		this.waiter = waiter;
	}
	
	public int getId() {
		return id;
	}
	
	private synchronized void think(){
		try {
			long time = (long) (Math.random() * 10000);
			PrintMessage.print("p"+id, " Thinking " + (time/1000) + " sec ");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
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
	
	private synchronized void releaseForks(boolean flag){
		if(flag){
			PrintMessage.print("p"+id, " Released forks. ");
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
