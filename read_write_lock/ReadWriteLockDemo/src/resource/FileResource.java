package resource;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class FileResource {

	private String message;
	private ReadWriteLock lock;
	private ReadLock readLock;
	private WriteLock writeLock;

	public FileResource(String message) {
		this.message = message;
		lock = new ReentrantReadWriteLock();
		writeLock = (WriteLock) lock.writeLock();
		readLock = (ReadLock) lock.readLock();
	}

	public String getMessage() {
		try {
			readLock.lock();
			return message;
		} 
		finally{
			readLock.unlock();
		}
	}

	public void setMessage(String message) {
		try{
			writeLock.lock();
			this.message += message;
			System.out.println("New message : " + this.message + " Thread : " + Thread.currentThread().getName());
		}
		finally{
			writeLock.unlock();
		}
	}
	
}
