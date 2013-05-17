package com.corba.test.server;


/**
 * Generated from IDL interface "functions".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at Sep 2, 2011 2:23:26 PM
 */

public abstract class functionsPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, com.corba.test.server.functionsOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "sql", new java.lang.Integer(0));
		m_opsHash.put ( "add", new java.lang.Integer(1));
	}
	private String[] ids = {"IDL:com/corba/test/server/functions:1.0"};
	public com.corba.test.server.functions _this()
	{
		return com.corba.test.server.functionsHelper.narrow(_this_object());
	}
	public com.corba.test.server.functions _this(org.omg.CORBA.ORB orb)
	{
		return com.corba.test.server.functionsHelper.narrow(_this_object(orb));
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // sql
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				sql(_arg0);
				break;
			}
			case 1: // add
			{
				short _arg0=_input.read_short();
				_out = handler.createReply();
				add(_arg0);
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
