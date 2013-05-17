package com.corba.test.client;

import java.util.Properties;

import com.corba.test.server.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class Client {
	public functions getServer() {
		functions func = null;
		try {
			Properties props = new Properties();
	        props.put("org.omg.CORBA.ORBInitialPort", "3001");
	        props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			ORB orb = ORB.init(new String[0], props);

			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			
			func = functionsHelper.narrow(ncRef.resolve_str("func"));
			//func = functionsHelper.narrow(ncRef.resolve(ncRef.to_name("func")));

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return func;
	}
} // class Client
