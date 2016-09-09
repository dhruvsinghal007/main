package resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Creates a array list with initial size 10, and two main methods to interact with it. Both methods block 
 * their execution conditionally.
 * @author Dhruv
 *
 */
public class ListEditor {
	private List<Integer> list;
	private Lock lock;
	private Condition isFull, isEmpty;
	
	public ListEditor() {
		list = new ArrayList<Integer>(10);
		lock = new ReentrantLock();
		isEmpty = lock.newCondition();
		isFull = lock.newCondition();
	}
	
	public void addElement(Integer ele){
		lock.lock();
		try {
			
			while(list.size() >= 10){
				System.out.print("\nList full... waiting for space...");
				isFull.await();
			}
			System.out.println("\nAdded : " + ele);
			list.add(ele);
			display();
			isEmpty.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
		
	}
	
	public void removeElement(){
		lock.lock();
		try {
			
			while(list.isEmpty()){
				System.out.print("\nList empty... waiting for a thread to add something...");
				isEmpty.await();
			}
			System.out.println("\nRemoved : " + list.remove(0));
			display();
			isFull.signal();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}
	
	public void display(){
		for(int i : list){
			System.out.print(i + " ");
		}
	}
}
