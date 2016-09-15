/**
 * 
 */
package dto;

import java.util.concurrent.Semaphore;

/**
 * contains the source String
 * @author Dhruv
 *
 */
public class FileResource {
	private String message;
	private Semaphore readLock;
	private Semaphore writeLock;
	
	public FileResource(String message) {
		this.message = message;
		readLock = new Semaphore(5);
		writeLock = new Semaphore(1);
	}
	
	public String getMessage() {
		return message;
	}
	
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
