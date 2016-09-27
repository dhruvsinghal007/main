package log;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

// TODO: Auto-generated Javadoc
/**
 * The Class LoggingExample. Just testing the working of info and error logging levels.
 */
public class LoggingExample {

	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(LoggingExample.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMConfigurator.configure("log4j.xml");
        	
		logger.info("info");
		
		logger.error("error");
	}

}
