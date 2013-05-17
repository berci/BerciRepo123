package com.berta.java.apache.logging;

import org.apache.log4j.Logger;

public class TestLogging {
	private static final Logger log = Logger.getLogger(TestLogging.class);
	
	public static void main(String[] args) {
		log.info("info");
		log.debug("debug");
		log.error("error");
		
		log.fatal("fatal");
		log.trace("trace");
		log.warn("warn");
	}
}
