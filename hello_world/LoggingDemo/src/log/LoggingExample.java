package log;

import org.apache.log4j.Logger;

public class LoggingExample {

	static final Logger logger = Logger.getLogger(LoggingExample.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("An info message !");
		logger.error("An error message !");
	}

}
