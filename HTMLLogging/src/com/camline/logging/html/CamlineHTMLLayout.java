package com.camline.logging.html;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class CamlineHTMLLayout extends HTMLLayout {
	
	private static final Object TRACE_PREFIX = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
	SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss,sss");
	
	@Override
	public String format(LoggingEvent event) {
		
		StringBuffer sbuf = new StringBuffer();
        sbuf.append("<tr id=\"" + event.getLevel() + "\">" + Layout.LINE_SEP);
        sbuf.append("<td valign=\"top\">");
        sbuf.append(format.format(new Date(event.timeStamp)));
        sbuf.append("</td>" + Layout.LINE_SEP);
        String escapedThread = Transform.escapeTags(event.getThreadName());
        sbuf.append("<td valign=\"top\" title=\"" + escapedThread + " thread\">");
        sbuf.append(escapedThread);
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("<td valign=\"top\" title=\"Level\">");
        if(event.getLevel().equals(Level.TRACE)) {
            sbuf.append("<font color=\"#000000\"><strong>");
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
            sbuf.append("</strong></font>");
        } else if(event.getLevel().equals(Level.INFO)) {
        	sbuf.append("<font color=\"#339933\"><strong>");
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
            sbuf.append("</strong></font>");
        } else if(event.getLevel().equals(Level.DEBUG)) {
        	sbuf.append("<font color=\"#3399FF\"><strong>");
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
            sbuf.append("</strong></font>");
        }
        else if(event.getLevel().isGreaterOrEqual(Level.WARN)) {
            sbuf.append("<font color=\"#993300\"><strong>");
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
            sbuf.append("</strong></font>");
        } else {
            sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
        }
        sbuf.append("</td>" + Layout.LINE_SEP);
        String escapedLogger = Transform.escapeTags(event.getLoggerName());
        try {
			Class<?> ss = Class.forName(event.getLoggerName());
			escapedLogger = ss.getSimpleName();
		} catch (ClassNotFoundException e) {
			// do nothing
		}
        sbuf.append("<td valign=\"top\" title=\"" + escapedLogger + " category\">");
        sbuf.append(escapedLogger);
        sbuf.append("</td>" + Layout.LINE_SEP);
        if(getLocationInfo())
        {
            LocationInfo locInfo = event.getLocationInformation();
            sbuf.append("<td valign=\"top\">");
            sbuf.append(Transform.escapeTags(locInfo.getFileName()));
            sbuf.append(':');
            sbuf.append(locInfo.getLineNumber());
            sbuf.append("</td>" + Layout.LINE_SEP);
        }
        sbuf.append("<td valign=\"top\" title=\"Message\">");
        
        String renderedMessage = Transform.escapeTags(event.getRenderedMessage());
        String message = renderedMessage.replace(" ", "&nbsp;").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace("\n", "<br>");
        sbuf.append(message);
        
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
	public String getHeader() {
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" + Layout.LINE_SEP);
        sbuf.append("<html>" + Layout.LINE_SEP);
        sbuf.append("<head>" + Layout.LINE_SEP);
        sbuf.append("<title>" + getTitle() + "</title>" + Layout.LINE_SEP);
        sbuf.append("<script type=\"text/javascript\">" + Layout.LINE_SEP);
        sbuf.append("function caller() {" + Layout.LINE_SEP);
        sbuf.append("var radios = document.getElementsByTagName('input');" + Layout.LINE_SEP);
        sbuf.append("var value;" + Layout.LINE_SEP);
        sbuf.append("for (var i = 0; i < radios.length; i++) {" + Layout.LINE_SEP);
        sbuf.append("if (radios[i].type === 'radio' && radios[i].checked) {" + Layout.LINE_SEP);
        sbuf.append("value = radios[i].value;" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("manageLogging(value);" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);	
        sbuf.append("function manageLogging(selectedLevel) {" + Layout.LINE_SEP);
        sbuf.append("var tbl = document.getElementById('tbl');" + Layout.LINE_SEP);
        sbuf.append("var numRows = tbl.rows.length;" + Layout.LINE_SEP);
        sbuf.append("for (var i = 0; i < numRows; i++) {" + Layout.LINE_SEP);
        sbuf.append("var actElementLevel = tbl.rows[i].id;" + Layout.LINE_SEP);
        sbuf.append("if(selectedLevel == \"TRACE\") {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"table-row\";" + Layout.LINE_SEP);
        sbuf.append("} else if(selectedLevel == \"DEBUG\") {" + Layout.LINE_SEP);
        sbuf.append("if(actElementLevel == \"TRACE\") {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"none\";" + Layout.LINE_SEP);
        sbuf.append("} else {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"table-row\";" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("} else if(selectedLevel == \"INFO\") {" + Layout.LINE_SEP);
        sbuf.append("if(actElementLevel == \"TRACE\" || actElementLevel == \"DEBUG\") {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"none\";" + Layout.LINE_SEP);
        sbuf.append("} else {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"table-row\";" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("} else if(selectedLevel == \"WARN\") {" + Layout.LINE_SEP);
        sbuf.append("if(actElementLevel == \"TRACE\" || actElementLevel == \"DEBUG\" || actElementLevel == \"INFO\") {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"none\";" + Layout.LINE_SEP);
        sbuf.append("} else {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"table-row\";" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("} else if(selectedLevel == \"ERROR\") {" + Layout.LINE_SEP);
        sbuf.append("if(actElementLevel == \"TRACE\" || actElementLevel == \"DEBUG\" || actElementLevel == \"INFO\" || actElementLevel == \"WARN\") {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"none\";" + Layout.LINE_SEP);
        sbuf.append("} else {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"table-row\";" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("} else if(selectedLevel == \"FATAL\") {" + Layout.LINE_SEP);
        sbuf.append("if(actElementLevel == \"TRACE\" || actElementLevel == \"DEBUG\" || actElementLevel == \"INFO\" || actElementLevel == \"WARN\" || actElementLevel == \"ERROR\") {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"none\";" + Layout.LINE_SEP);
        sbuf.append("} else {" + Layout.LINE_SEP);
        sbuf.append("tbl.rows[i].style.display=\"table-row\";" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("}" + Layout.LINE_SEP);
        sbuf.append("</script>" + Layout.LINE_SEP);
        sbuf.append("<style type=\"text/css\">" + Layout.LINE_SEP);
        sbuf.append("body, table {font-family: arial,sans-serif; font-size: small;}" + Layout.LINE_SEP);
        sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
        sbuf.append("td {padding:5px; white-space:nowrap}" + Layout.LINE_SEP);
        sbuf.append("</style>" + Layout.LINE_SEP);
        sbuf.append("</head>" + Layout.LINE_SEP);
        sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\" onload=\"caller()\">" + Layout.LINE_SEP);
        sbuf.append("<br>" + Layout.LINE_SEP);
        sbuf.append("<form>" + Layout.LINE_SEP);
        sbuf.append("<input id=\"TRACE\" type=\"radio\"  name=\"level\" value=\"TRACE\" onclick=\"manageLogging(this.value)\">Trace" + Layout.LINE_SEP);
        sbuf.append("<input id=\"DEBUG\" type=\"radio\" name=\"level\" value=\"DEBUG\" onclick=\"manageLogging(this.value)\" checked=\"checked\">Debug" + Layout.LINE_SEP);
        sbuf.append("<input id=\"INFO\" type=\"radio\" name=\"level\" value=\"INFO\" onclick=\"manageLogging(this.value)\">Info" + Layout.LINE_SEP);
        sbuf.append("<input id=\"WARN\" type=\"radio\" name=\"level\" value=\"WARN\" onclick=\"manageLogging(this.value)\">Warn" + Layout.LINE_SEP);
        sbuf.append("<input id=\"ERROR\" type=\"radio\" name=\"level\" value=\"ERROR\" onclick=\"manageLogging(this.value)\">Error" + Layout.LINE_SEP);
        sbuf.append("<input id=\"FATAL\" type=\"radio\" name=\"level\" value=\"FATAL\" onclick=\"manageLogging(this.value)\">Fatal" + Layout.LINE_SEP);
       	sbuf.append("</form>" + Layout.LINE_SEP);
       	sbuf.append("<br>" + Layout.LINE_SEP);
        sbuf.append("<table id=\"tbl\" cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"auto\">" + Layout.LINE_SEP);
        
		return sbuf.toString();
	}
}
