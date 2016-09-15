package tester;

import resource.ListEditor;
import thread.Consumer;
import thread.Producer;

public class ConditionTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListEditor editor = new ListEditor();
		new Thread(new Producer(editor)).start();
		new Thread(new Consumer(editor)).start();
	}

}
