package com.corba.test.server;


/**
 * Generated from IDL interface "functions".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at Sep 2, 2011 2:23:27 PM
 */

public final class functionsHelper
{
	public static void insert (final org.omg.CORBA.Any any, final com.corba.test.server.functions s)
	{
			any.insert_Object(s);
	}
	public static com.corba.test.server.functions extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:com/corba/test/server/functions:1.0", "functions");
	}
	public static String id()
	{
		return "IDL:com/corba/test/server/functions:1.0";
	}
	public static functions read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(com.corba.test.server._functionsStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final com.corba.test.server.functions s)
	{
		_out.write_Object(s);
	}
	public static com.corba.test.server.functions narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof com.corba.test.server.functions)
		{
			return (com.corba.test.server.functions)obj;
		}
		else if (obj._is_a("IDL:com/corba/test/server/functions:1.0"))
		{
			com.corba.test.server._functionsStub stub;
			stub = new com.corba.test.server._functionsStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static com.corba.test.server.functions unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof com.corba.test.server.functions)
		{
			return (com.corba.test.server.functions)obj;
		}
		else
		{
			com.corba.test.server._functionsStub stub;
			stub = new com.corba.test.server._functionsStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
