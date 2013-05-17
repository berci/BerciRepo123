package com.berta.server;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.server.XmlRpcStreamServer;
import org.apache.xmlrpc.webserver.WebServer;

public class XmlRpcServer {

	public static void main(String[] args) {
		try {
			WebServer webServer = new WebServer(1089);

			XmlRpcStreamServer xmlRpcServer = webServer.getXmlRpcServer();

			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			/*
			 * Load handler definitions from a property file. The property file
			 * might look like: Calculator=org.apache.xmlrpc.demo.Calculator
			 * org.
			 * apache.xmlrpc.demo.proxy.Adder=org.apache.xmlrpc.demo.proxy.AdderImpl
			 */
			//phm.load(Thread.currentThread().getContextClassLoader(), "MyHandlers.properties");

			/*
			 * You may also provide the handler classes directly, like this:
			 * phm.addHandler("Calculator",
			 * org.apache.xmlrpc.demo.Calculator.class);
			 * phm.addHandler(org.apache
			 * .xmlrpc.demo.proxy.Adder.class.getName(),
			 * org.apache.xmlrpc.demo.proxy.AdderImpl.class);
			 */
			phm.addHandler(com.berta.server.Adder.class.getName(), com.berta.server.AdderImpl.class);
			phm.addHandler("Test", com.berta.server.XmlRpcHandler.class);
					 
			xmlRpcServer.setHandlerMapping(phm);

			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer
					.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(false);

			webServer.start();
			
		} catch (XmlRpcException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
