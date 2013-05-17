package com.corba.test.server;

/**
 * Generated from IDL interface "functions".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at Sep 2, 2011 2:23:26 PM
 */

public final class functionsHolder	implements org.omg.CORBA.portable.Streamable{
	 public functions value;
	public functionsHolder()
	{
	}
	public functionsHolder (final functions initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return functionsHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = functionsHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		functionsHelper.write (_out,value);
	}
}
