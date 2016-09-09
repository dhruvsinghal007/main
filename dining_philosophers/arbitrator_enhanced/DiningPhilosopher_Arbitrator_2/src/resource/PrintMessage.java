package resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PrintMessage implements Runnable{
	private static BlockingQueue<Message> bQueue;
	private Map<String, String> map;
	
	public PrintMessage() {
		PrintMessage.bQueue = new LinkedBlockingQueue<Message>();
		this.map = new HashMap<String, String>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Message msg = bQueue.take();
				map.put(msg.getId(), msg.getMessage());
				System.out.println(map);
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
