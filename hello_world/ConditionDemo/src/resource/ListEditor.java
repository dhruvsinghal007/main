package resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// TODO: Auto-generated Javadoc
/**
 * The class ListEditor
 * Creates a array list with initial size 10, and two main methods to interact with it. Both methods block 
 * their execution conditionally.
 * @author Dhruv
 *
 */
public class ListEditor {
	
	/** The list. */
	private List<Integer> list;
	
	/** The lock. */
	private Lock lock;
	
	/** Two Condition objects for testing full and emptiness. */
	private Condition isFull, isEmpty;
	
	/**
	 * Instantiates a new list editor.
	 */
	public ListEditor() {
		list = new ArrayList<Integer>(10);
		lock = new ReentrantLock();
		isEmpty = lock.newCondition();
		isFull = lock.newCondition();
	}
	
	/**
	 * Adds the element.
	 *
	 * @param ele the integer
	 */
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
	
	/**
	 * Removes the element.
	 */
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
	
	/**
	 * Display the list
	 */
	public void display(){
		for(int i : list){
			System.out.print(i + " ");
		}
	}
}
