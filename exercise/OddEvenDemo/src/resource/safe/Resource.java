package resource.safe;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Resource {
	private int number;
	private ReadWriteLock lock;
	private ReadLock rLock;
	private WriteLock wLock;

	public Resource(int number) {
		this.number = number;
		lock = new ReentrantReadWriteLock();
		rLock = (ReadLock) lock.readLock();
		wLock = (WriteLock) lock.writeLock();
	}

	public Integer getNumber() {
		try{
			rLock.lock();
			return number;
		}
		finally{
			rLock.unlock();
		}
	}

	public void setNumber(int integer) {
		wLock.lock();
		this.number = integer;
		wLock.unlock();
	}
}
