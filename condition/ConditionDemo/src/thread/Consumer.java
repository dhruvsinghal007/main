package thread;

import resource.ListEditor;

public class Consumer implements Runnable {
	
	private ListEditor editor;

	public Consumer(ListEditor editor) {
		this.editor = editor;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			editor.removeElement();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
