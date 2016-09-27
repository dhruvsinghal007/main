package tester;

import java.util.PriorityQueue;

import thread.Worker;

// TODO: Auto-generated Javadoc
/**
 * The Class PriorityQueueTester.
 */
public class PriorityQueueTester {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<String> queue = new PriorityQueue<>();
		new Thread(new Worker(queue)).start();
	}

}
