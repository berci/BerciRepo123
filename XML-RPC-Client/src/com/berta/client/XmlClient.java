package com.berta.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;

import com.berta.server.Adder;
import com.berta.server.Test;

public class XmlClient {

	public static void main(String[] args) {
		// create configuration
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
        	URL url = new URL("http", "localhost", 1089, "");
			//config.setServerURL(new URL("http://127.0.0.1:1089"));
			config.setServerURL(url);
	        
			config.setEnabledForExtensions(true);  
	        config.setConnectionTimeout(60 * 1000);
	        config.setReplyTimeout(60 * 1000);
	
	        XmlRpcClient client = new XmlRpcClient();
	      
	        // use Commons HttpClient as transport
	        client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
	        // set configuration
	        client.setConfig(config);
	
	        // make the a regular call
	        Object[] params = new Object[]{new String("Bela")};
	        Test result = (Test) client.execute("Test.sayHello", params);
	        System.out.println(result.getName());
	      
	        // make a call using dynamic proxy
	        ClientFactory factory = new ClientFactory(client);
	        Adder adder = (Adder) factory.newInstance(Adder.class);
	        System.out.println(adder.get("Laci").getName());
	        
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}

}
