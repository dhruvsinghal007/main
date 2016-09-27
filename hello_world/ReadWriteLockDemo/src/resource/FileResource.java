package resource;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

// TODO: Auto-generated Javadoc
/**
 * The Class FileResource.
 */
public class FileResource {

	/** The message. */
	private String message;
	
	/** The lock. */
	private ReadWriteLock lock;
	
	/** The read lock. */
	private ReadLock readLock;
	
	/** The write lock. */
	private WriteLock writeLock;

	/**
	 * Instantiates a new file resource.
	 *
	 * @param message the message
	 */
	public FileResource(String message) {
		this.message = message;
		lock = new ReentrantReadWriteLock();
		writeLock = (WriteLock) lock.writeLock();
		readLock = (ReadLock) lock.readLock();
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		try {
			readLock.lock();
			return message;
		} 
		finally{
			readLock.unlock();
		}
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
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
