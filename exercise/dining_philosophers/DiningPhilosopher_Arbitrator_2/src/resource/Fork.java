package resource;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

// TODO: Auto-generated Javadoc
/**
 * Class Fork
 * class describes the possible functionalities of a fork. Has id, state, readwrite lock (for thread safety)
 * @author Dhruv
 *
 */
public class Fork {
	
	/** The id. */
	private int id;
	
	/** The state. */
	private ForkState state;
	
	/** The lock. */
	private ReadWriteLock lock;
	
	/** The read lock. */
	private ReadLock readLock;
	
	/** The write lock. */
	private WriteLock writeLock;
	
	/**
	 * Instantiates a new fork.
	 *
	 * @param id the id
	 * @param state the state
	 */
	public Fork(int id, ForkState state) {
		this.id = id;
		this.state = state;
		lock = new ReentrantReadWriteLock();
		readLock = (ReadLock) lock.readLock();
		writeLock = (WriteLock) lock.writeLock();
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public ForkState getState() {
		try{
			readLock.lock();
			return state;
		}
		finally{
			readLock.unlock();
		}
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(ForkState state) {
		writeLock.lock();
		this.state = state;
		writeLock.unlock();
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
