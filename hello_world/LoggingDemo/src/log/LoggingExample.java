package log;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggingExample {

	static final Logger logger = Logger.getLogger(LoggingExample.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMConfigurator.configure("log4j.xml");
        	
		logger.info("info");
		
		logger.error("error");
	}

}
