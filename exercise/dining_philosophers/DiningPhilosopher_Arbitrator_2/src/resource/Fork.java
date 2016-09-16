package resource;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * class describes the possible functionalities of a fork. 
 * @author Dhruv
 *
 */
public class Fork {
	private int id;
	private ForkState state;
	private ReadWriteLock lock;
	private ReadLock readLock;
	private WriteLock writeLock;
	
	public Fork(int id, ForkState state) {
		this.id = id;
		this.state = state;
		lock = new ReentrantReadWriteLock();
		readLock = (ReadLock) lock.readLock();
		writeLock = (WriteLock) lock.writeLock();
	}
	public ForkState getState() {
		try{
			readLock.lock();
			return state;
		}
		finally{
			readLock.unlock();
		}
	}
	public void setState(ForkState state) {
		writeLock.lock();
		this.state = state;
		writeLock.unlock();
	}
	public int getId() {
		return id;
	}
}
