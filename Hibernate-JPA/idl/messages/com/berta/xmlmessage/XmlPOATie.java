package com.berta.xmlmessage;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Xml".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at 01.03.2013 16:14:31
 */

public class XmlPOATie
	extends XmlPOA
{
	private XmlOperations _delegate;

	private POA _poa;
	public XmlPOATie(XmlOperations delegate)
	{
		_delegate = delegate;
	}
	public XmlPOATie(XmlOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public com.berta.xmlmessage.Xml _this()
	{
		return com.berta.xmlmessage.XmlHelper.narrow(_this_object());
	}
	public com.berta.xmlmessage.Xml _this(org.omg.CORBA.ORB orb)
	{
		return com.berta.xmlmessage.XmlHelper.narrow(_this_object(orb));
	}
	public XmlOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(XmlOperations delegate)
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
	public void process_request(com.berta.xmlmessage.XmlPackage.XmlDocumentHolder document)
	{
_delegate.process_request(document);
	}

}
