package com.berta.xmlmessage;


/**
 * Generated from IDL interface "Xml".
 *
 * @author JacORB IDL compiler V 2.3.0, 17-Feb-2007
 * @version generated at 01.03.2013 16:14:31
 */

public class _XmlStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements com.berta.xmlmessage.Xml
{
	private String[] ids = {"IDL:com/berta/xmlmessage/Xml:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = com.berta.xmlmessage.XmlOperations.class;
	public void process_request(com.berta.xmlmessage.XmlPackage.XmlDocumentHolder document)
	{
		while(true)
		{
		if(! this._is_local())
		{
			org.omg.CORBA.portable.InputStream _is = null;
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "process_request", true);
				com.berta.xmlmessage.XmlPackage.XmlDocumentHelper.write(_os,document.value);
				_is = _invoke(_os);
				document.value = com.berta.xmlmessage.XmlPackage.XmlDocumentHelper.read(_is);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx ){}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
				String _id = _ax.getId();
				throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "process_request", _opsClass );
			if( _so == null )
				throw new org.omg.CORBA.UNKNOWN("local invocations not supported!");
			XmlOperations _localServant = (XmlOperations)_so.servant;
			try
			{
				_localServant.process_request(document);
			}
			finally
			{
				_servant_postinvoke(_so);
			}
			return;
		}

		}

	}

}
