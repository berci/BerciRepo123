package com.camline.logging.html;

import org.apache.log4j.Logger;

public class HTMLLoggingTest {
	
	private static final Logger log = Logger.getLogger(HTMLLoggingTest.class);
	
	public static void main(String[] args) {
		log.info("info");
		log.debug("debug");
		log.error("error");
		
		log.fatal("fatal");
		log.trace("trace");
		log.warn("warn");
		
		try{
			Long.parseLong("dd");
		} catch (Exception e) {
			log.error("Wrong format! " , e);
		}	
		
		for(int i = 0; i < 100; i++) {
			try {
				log.debug("debug " + i);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
