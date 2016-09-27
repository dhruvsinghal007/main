/**
 * 
 */
package dto;

import java.util.concurrent.Semaphore;

// TODO: Auto-generated Javadoc
/**
 * Class FileResource
 * contains the source String.
 *
 * @author Dhruv
 */
public class FileResource {
	
	/** The message. */
	private String message;
	
	/** The read lock. */
	private Semaphore readLock;
	
	/** The write lock. */
	private Semaphore writeLock;
	
	/**
	 * Instantiates a new file resource.
	 *
	 * @param message the message
	 */
	public FileResource(String message) {
		this.message = message;
		readLock = new Semaphore(5);
		writeLock = new Semaphore(1);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Prints the message.
	 */
	public void printMessage(){
		try {
			readLock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Reading : " + message + " by " + Thread.currentThread().getName());
		readLock.release();
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		try {
			writeLock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.message += message;
		System.out.println("New message : " + this.message + " Thread : " + Thread.currentThread().getName());
		writeLock.release();
	}
	
}
