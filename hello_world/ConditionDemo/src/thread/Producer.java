package thread;

import resource.ListEditor;

public class Producer implements Runnable {
	
	private ListEditor editor;

	public Producer(ListEditor editor) {
		this.editor = editor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(i++ >= 0){
			editor.addElement(i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
