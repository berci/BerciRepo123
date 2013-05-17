package com.berta.xmlmessage;

/**
 * Generated from IDL interface "Xml".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at 01.03.2013 16:14:31
 */

public final class XmlHolder	implements org.omg.CORBA.portable.Streamable{
	 public Xml value;
	public XmlHolder()
	{
	}
	public XmlHolder (final Xml initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return XmlHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = XmlHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		XmlHelper.write (_out,value);
	}
}
