package com.corba.test;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server {

	public static void main(String[] args) {
	  try {
		  Properties props = new Properties();
		  props.put("org.omg.CORBA.ORBInitialPort", "3001");
		  props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		  ORB orb = ORB.init(args, props);

	      NamingContextExt ncRef = NamingContextExtHelper.narrow (orb.resolve_initial_references ("NameService"));

	      POA rootpoa = POAHelper.narrow (orb.resolve_initial_references ("RootPOA"));
	      rootpoa.the_POAManager().activate();

	      org.omg.CORBA.Object obj_ref = rootpoa.servant_to_reference (new TestServer ());
	      
	      //functions func = functionsHelper.narrow (obj_ref);

	      ncRef.rebind (ncRef.to_name ("func"), obj_ref);

	      System.out.println ("Server launched, ready, and waiting on clients...");
	      orb.run();
	    } // try
	    catch (Exception e){
	      System.err.println("ERROR: " + e);
	      e.printStackTrace(System.out);
	    }
  }
}

