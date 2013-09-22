package com.camline.logging.html;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.RollingFileAppender;

public class CamlineRollingFileAppender extends RollingFileAppender {
	
	@Override
	protected void writeHeader() {
		
		if(super.layout != null) {
            String h = super.layout.getHeader();
            if(h != null && qw != null) {

            	String file = getFile();
            	BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader(file));
					while (in.ready()) {
						String line = in.readLine().trim();
						
						if (line.contains("HTML")) {
							return;
						}
					}	
					
					qw.write(h);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
				}
            }
        }
	}
	
	
}
