package com.berta.xmlmessage;


/**
 * Generated from IDL interface "Xml".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at 01.03.2013 16:14:31
 */

public final class XmlHelper
{
	public static void insert (final org.omg.CORBA.Any any, final com.berta.xmlmessage.Xml s)
	{
			any.insert_Object(s);
	}
	public static com.berta.xmlmessage.Xml extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static org.omg.CORBA.TypeCode type()
	{
		return org.omg.CORBA.ORB.init().create_interface_tc("IDL:com/berta/xmlmessage/Xml:1.0", "Xml");
	}
	public static String id()
	{
		return "IDL:com/berta/xmlmessage/Xml:1.0";
	}
	public static Xml read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object());
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final com.berta.xmlmessage.Xml s)
	{
		_out.write_Object(s);
	}
	public static com.berta.xmlmessage.Xml narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof com.berta.xmlmessage.Xml)
		{
			return (com.berta.xmlmessage.Xml)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static com.berta.xmlmessage.Xml unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof com.berta.xmlmessage.Xml)
		{
			return (com.berta.xmlmessage.Xml)obj;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
}
