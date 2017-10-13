package net.sinou.tutorials.springbatch.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void performTask() {
		logger.info("This is an {} message.", "info");
		logger.warn("This is a warn message.");
		logger.error("This is an error message.");
		logger.debug("This is a debug message.");
		// logger.debug("Current used logger: " + logger.getClass().getName());
		// ch.qos.logback.classic.Logger obj;
	}

	public static void main(String[] args) {
		new LogbackDemo().performTask();
	}
}
