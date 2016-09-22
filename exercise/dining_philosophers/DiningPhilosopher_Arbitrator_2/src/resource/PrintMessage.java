package resource;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class PrintMessage implements Runnable{
	
	private static BlockingQueue<Message> bQueue;
	private Map<String, String> map;
	static final Logger logger = Logger.getLogger(PrintMessage.class);
	
	public PrintMessage() {
		PrintMessage.bQueue = new LinkedBlockingQueue<Message>();
		this.map = new HashMap<String, String>();
	}

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
	
	public static void print(String id, String status){
		Message message = new Message(id, status);
		try {
			bQueue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
