package com.berta.xmlmessage.XmlPackage;

/**
 * Generated from IDL alias "XmlDocument".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at 01.03.2013 16:14:31
 */

public final class XmlDocumentHolder
	implements org.omg.CORBA.portable.Streamable
{
	public byte[] value;

	public XmlDocumentHolder ()
	{
	}
	public XmlDocumentHolder (final byte[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return XmlDocumentHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = XmlDocumentHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		XmlDocumentHelper.write (out,value);
	}
}
