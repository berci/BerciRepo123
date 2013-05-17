package com.camline.logging.html;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class MyHTMLLayout extends HTMLLayout {
	
	private static final Object TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss,sss");

	@Override
	public String format(LoggingEvent event) {
		// Check template HTML file with header and footer
		createTemplateHTMLFile(event);
		
		StringBuffer sbuf = new StringBuffer();
        sbuf.append(Layout.LINE_SEP + "<tr id=\"" + event.getLevel() + "\">" + Layout.LINE_SEP);
        sbuf.append("<td>");
        sbuf.append(format.format(new Date(event.timeStamp)));
        sbuf.append("</td>" + Layout.LINE_SEP);
        String escapedThread = Transform.escapeTags(event.getThreadName());
        sbuf.append("<td title=\"" + escapedThread + " thread\">");
        sbuf.append(escapedThread);
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("<td title=\"Level\">");
        if(event.getLevel().equals(Level.DEBUG))
        {
            sbuf.append("<font color=\"#339933\">");
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
            sbuf.append("</font>");
        } else
        if(event.getLevel().isGreaterOrEqual(Level.WARN))
        {
            sbuf.append("<font color=\"#993300\"><strong>");
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
            sbuf.append("</strong></font>");
        } else
        {
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
        }
        sbuf.append("</td>" + Layout.LINE_SEP);
        String escapedLogger = Transform.escapeTags(event.getLoggerName());
        sbuf.append("<td title=\"" + escapedLogger + " category\">");
        sbuf.append(escapedLogger);
        sbuf.append("</td>" + Layout.LINE_SEP);
        if(getLocationInfo())
        {
            LocationInfo locInfo = event.getLocationInformation();
            sbuf.append("<td>");
            sbuf.append(Transform.escapeTags(locInfo.getFileName()));
            sbuf.append(':');
            sbuf.append(locInfo.getLineNumber());
            sbuf.append("</td>" + Layout.LINE_SEP);
        }
        sbuf.append("<td title=\"Message\">");
        sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP);
        if(event.getNDC() != null)
        {
            sbuf.append("<tr id=\"" + event.getLevel() + "\"><td bgcolor=\"#EEEEEE\" style=\"font-size : small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
            sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));
            sbuf.append("</td></tr>" + Layout.LINE_SEP);
        }
        String s[] = event.getThrowableStrRep();
        if(s != null)
        {
            sbuf.append("<tr id=\"" + event.getLevel() + "\"><td bgcolor=\"#993300\" style=\"color:White; font-size : small;\" colspan=\"6\">");
            appendThrowableAsHTML(s, sbuf);
            sbuf.append("</td></tr>" + Layout.LINE_SEP);
        }
        return sbuf.toString();
	}
	
	/**
	 * Create template HTML file with header and footer if it is not exist
	 * @param event
	 */
	private void createTemplateHTMLFile(LoggingEvent event) {
		try {
			Enumeration<Appender> allAppenders = Logger.getRootLogger().getAllAppenders();;
			while(allAppenders.hasMoreElements()) {
				Appender nextElement = allAppenders.nextElement();
				if(nextElement instanceof FileAppender) {
					if(nextElement.getLayout() instanceof MyHTMLLayout) {
						
						String path = ((FileAppender) nextElement).getFile();
						File file = new File(path);
						File templateHTML = new File(file.getParent() + File.separator + "_" + file.getName());
						
						if(!templateHTML.exists()) {
							templateHTML.createNewFile();
							templateHTML.setLastModified(new SimpleDateFormat("MM/dd/yyyy").parse("12/31/2019").getTime());
						}	
					}
				}	
			}
		} catch (IOException e) {
			// ignore
		} catch (ParseException e) {
			// ignore;
		}
	}

	static void appendThrowableAsHTML(String s[], StringBuffer sbuf) {
        if(s != null) {
            int len = s.length;
            if(len == 0)
                return;
            sbuf.append(Transform.escapeTags(s[0]));
            sbuf.append(Layout.LINE_SEP);
            for(int i = 1; i < len; i++) {
                sbuf.append(TRACE_PREFIX);
                sbuf.append(Transform.escapeTags(s[i]));
                sbuf.append(Layout.LINE_SEP);
            }
        }
    }
	
	@Override
	public String getFooter() {
		return null;
	}
	
	@Override
	public String getHeader() {
		return null;
	}
}
