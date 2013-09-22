package com.camline.logging.html;

import org.apache.log4j.Logger;

public class HTMLLoggingTest {
	
	private static final Logger log = Logger.getLogger(HTMLLoggingTest.class);
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("hello\n");
		sb.append("  hello\n");
		sb.append("    hello\n");
		sb.append("      hello\n");
		
		sb.append("\thello22\n");
		sb.append("\t\thello22\n");
		sb.append("\t\t\thello22\n");
		
		log.trace(sb.toString(), new Exception("trace"));
		log.info(sb.toString(), new Exception("info"));
		log.debug(sb.toString(), new Exception("debug"));
		log.error(sb.toString(), new Exception("error"));
		log.warn(sb.toString(), new Exception("warn"));
		log.fatal(sb.toString(), new Exception("fatal"));
//		log.info("info");
//		log.debug("debug");
//		log.error("error");
//		
//		log.fatal("fatal");
//		log.trace("trace");
//		log.warn("warn");
//		
//		try{
//			Long.parseLong("dd");
//		} catch (Exception e) {
//			log.error("Wrong format! " , e);
//		}	
//		
//		for(int i = 0; i < 1000; i++) {
//			try {
//				log.debug("debug " + i);
//				Thread.sleep(2000);
//				if(i % 10 == 0) {
//					try{
//						Long.parseLong(i + "aaa");
//					} catch (Exception e) {
//						log.error("Wrong format! " , e);
//					}	
//				}
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}	
	}
}
