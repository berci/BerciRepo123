package rmi.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.berta.api.jaxb.GetAllEventRequest;
import com.berta.server.impl.HelloInterface;

public class HelloClient {

	/**
	 * Entry Point to this Application
	 */
	public static void main(String[] args) {

		try {
			Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1089);
			HelloInterface impl = (HelloInterface) myRegistry.lookup("myMessage");
			
			GetAllEventRequest request = new GetAllEventRequest();
			
			ByteArrayOutputStream baos = marshal(request);
			byte[] byteArray = baos.toByteArray();
			byte[] bais = impl.xmlCall(byteArray);
			System.out.println(bais);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	protected static ByteArrayOutputStream marshal(Object request) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			APIMarshaller.marshalRequest(request, baos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baos;
	}
}
