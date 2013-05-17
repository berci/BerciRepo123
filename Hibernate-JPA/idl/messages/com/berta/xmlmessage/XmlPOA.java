package com.berta.xmlmessage;


/**
 * Generated from IDL interface "Xml".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at 01.03.2013 16:14:31
 */

public abstract class XmlPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, com.berta.xmlmessage.XmlOperations
{
	static private final java.util.Hashtable m_opsHash = new java.util.Hashtable();
	static
	{
		m_opsHash.put ( "process_request", new java.lang.Integer(0));
	}
	private String[] ids = {"IDL:com/berta/xmlmessage/Xml:1.0"};
	public com.berta.xmlmessage.Xml _this()
	{
		return com.berta.xmlmessage.XmlHelper.narrow(_this_object());
	}
	public com.berta.xmlmessage.Xml _this(org.omg.CORBA.ORB orb)
	{
		return com.berta.xmlmessage.XmlHelper.narrow(_this_object(orb));
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
			case 0: // process_request
			{
				com.berta.xmlmessage.XmlPackage.XmlDocumentHolder _arg0= new com.berta.xmlmessage.XmlPackage.XmlDocumentHolder();
				_arg0._read (_input);
				_out = handler.createReply();
				process_request(_arg0);
				com.berta.xmlmessage.XmlPackage.XmlDocumentHelper.write(_out,_arg0.value);
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
