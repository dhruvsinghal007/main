package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import resource.Fork;
import resource.ForkState;
import resource.PrintMessage;
import thread.Philosopher;

/**
 * waiter keeps the lock and does synchronization on every request and also keeps the track of requests, 
 * updates the fork states after each request if successful and interacts with philosopher. More than 
 * one can eat if their forks are available
 * @author Dhruv
 *
 */
public class Waiter implements Runnable{
	private Fork[] forks;
	private BlockingQueue<Philosopher> queue;
	public Waiter(Fork[] forks) {
		this.forks = forks;
		queue = new LinkedBlockingQueue<Philosopher>();
		new Semaphore(1);
	}
	
	public synchronized void addRequest(Philosopher philosopher){
		try {
			if(!(queue.contains(philosopher))){
				queue.put(philosopher);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * find the appropriate forks, check their availability and if both available,
	 * update their status to not available
	 */
	private void giveForks(){
		if(queue.size() > 0){
			String q = " Queue status : [";
			for(Philosopher ph : queue){
				q = q + ph.getId() + " ";
			}
			q = q + "] ";
			PrintMessage.print("W", q);
			try {
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
					Philosopher ph = queue.take();
					ph.setForks(forkList);
					synchronized (ph) {
						ph.notify();
					}
				}
				
				Thread.sleep(2000);
								
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * only called when philosopher calls. So it is guaranteed that both forks will be there ,
	 * just update their status to available.
	 */
	public synchronized void takeForks(List<Fork> list){
		
		for(Fork fork : list){
			for(int i = 0 ; i < forks.length ; i++){
				if(forks[i].getId() == fork.getId()){
					forks[i].setState(ForkState.AVAILABLE);
				}
			}
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			giveForks();
		}
	}
}
