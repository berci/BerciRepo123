package com.corba.test.server;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "functions".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at Sep 2, 2011 2:23:26 PM
 */

public class functionsPOATie
	extends functionsPOA
{
	private functionsOperations _delegate;

	private POA _poa;
	public functionsPOATie(functionsOperations delegate)
	{
		_delegate = delegate;
	}
	public functionsPOATie(functionsOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public com.corba.test.server.functions _this()
	{
		return com.corba.test.server.functionsHelper.narrow(_this_object());
	}
	public com.corba.test.server.functions _this(org.omg.CORBA.ORB orb)
	{
		return com.corba.test.server.functionsHelper.narrow(_this_object(orb));
	}
	public functionsOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(functionsOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void sql(java.lang.String sql)
	{
_delegate.sql(sql);
	}

	public void add(short data)
	{
_delegate.add(data);
	}

}
