package tester;

import java.util.PriorityQueue;

import thread.Worker;

public class PriorityQueueTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<String> queue = new PriorityQueue<>();
		new Thread(new Worker(queue)).start();
	}

}
