package thread;

import resource.ListEditor;

// TODO: Auto-generated Javadoc
/**
 * The Class Producer.
 */
public class Producer implements Runnable {
	
	/** The editor. */
	private ListEditor editor;

	/**
	 * Instantiates a new producer.
	 *
	 * @param editor the editor
	 */
	public Producer(ListEditor editor) {
		this.editor = editor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
