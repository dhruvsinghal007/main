package resource;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

// TODO: Auto-generated Javadoc
/**
 * The Class PrintMessage.
 * Maintains a map of id-message pairs. Every time a request comes, it is added into blocking queue, and every element of blocking queue is then retrieved one at a time and put into map. The whole map is printed after each updation.
 */
public class PrintMessage implements Runnable{
	
	/** The message blocking queue. */
	private static BlockingQueue<Message> bQueue;
	
	/** The map to pair id and message. */
	private Map<String, String> map;
	
	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(PrintMessage.class);
	
	/**
	 * Instantiates a new printMessage with map and blocking queue.
	 */
	public PrintMessage() {
		PrintMessage.bQueue = new LinkedBlockingQueue<Message>();
		this.map = new HashMap<String, String>();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * retrieves top element from blocking queue and updates map. After that logs the whole map.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		URL u = PrintMessage.class.getClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(u);
		while(true){
			try {
				Message msg = bQueue.take();
				map.put(msg.getId(), msg.getMessage());
				//System.out.println(map);
				logger.info(map);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * puts the incoming message request with id into blocking queue for printing.
	 *
	 * @param id the id
	 * @param status the status
	 */
	public static void print(String id, String status){
		Message message = new Message(id, status);
		try {
			bQueue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
