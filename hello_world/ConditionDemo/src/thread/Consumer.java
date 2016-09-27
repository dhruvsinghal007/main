package thread;

import resource.ListEditor;

// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 */
public class Consumer implements Runnable {
	
	/** The editor. */
	private ListEditor editor;

	/**
	 * Instantiates a new consumer.
	 *
	 * @param editor the editor
	 */
	public Consumer(ListEditor editor) {
		this.editor = editor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
