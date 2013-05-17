/*
 * Created on Mar 31, 2005
 */
package com.berta.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

public class APIMarshaller {
	
	private static boolean useThreadContextClassLoader = false;
	
	
	private static Map<String, JAXBContext> jaxbContextCache = new HashMap<String, JAXBContext>();

	/**
	 * Sun's JAXB FAQ states that their implementation of JAXBContext is thread-safe.
	 * So let's create a cache for them.
	 * We try to get the 99,99% case fast and unsychronized.
	 * 
	 * @param klass one of the jaxb classes which you want to bind/unbind
	 * @return a JAXBContext
	 * @throws JAXBException
	 */
	private static JAXBContext getJAXBContext(Class<?> klass) throws JAXBException {
		String packageName = klass.getPackage().getName();
		
		// Quick unsychronized try, if it works, hurray!
		JAXBContext context = jaxbContextCache.get(packageName);
		if (context != null) {
			return context;
		}
		
		// Dammit. Not created yet. So let's do the slow synchronized way...
		synchronized (jaxbContextCache) {
			// Need to lookup again. It may be created in between these few lines of code...
			context = jaxbContextCache.get(packageName);
			if (context != null) {
				return context;
			}
			
			if (useThreadContextClassLoader) {
				context = JAXBContext.newInstance(klass.getPackage().getName());
			} else {
				context = JAXBContext.newInstance(klass.getPackage().getName(), klass.getClassLoader());
			}
			jaxbContextCache.put(packageName, context);
			return context;
		}
	}
	
	private static Writer createWriter(OutputStream outputstream)
			throws UnsupportedEncodingException {
		return new OutputStreamWriter(outputstream, "UTF-8");
	}

	private static Marshaller createMarshaller(Class<?> klass)
			throws JAXBException {
		return getJAXBContext(klass).createMarshaller();
	}

	/**
	 * This method marshals a request and maps errors to an SOException.
	 * 
	 * @param obj request object
	 * @param outputstream target outputstream
	 * @throws GenericErrorException on validation, marshal or other errors
	 */
	public static void marshalRequest(Object obj, OutputStream outputstream) {
		try {
			Writer writer = createWriter(outputstream);
			Marshaller marshaller = createMarshaller(obj.getClass());
			marshaller.marshal(obj, writer);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			//
		} catch (JAXBException e) {
			//
		} catch (IOException e) {
			//	
		}
	}

	/**
	 * This method can be used to put an empty response object in the
	 * output stream.
	 * 
	 * @param outputstream target outputstream
	 * @throws GenericErrorException on validation, marshal or other errors
	 */
	public static void marshalEmptyResponse(OutputStream outputstream) {
		try {
			Writer writer = createWriter(outputstream);
			//marshalResponse(new EmptyResponse(), writer);
		} catch (UnsupportedEncodingException e) {
			//
		}
	}

	/**
	 * This method marshals a response and maps errors to an SOException.
	 * 
	 * @param obj response object
	 * @param outputstream target outputstream
	 * @throws GenericErrorException on validation, marshal or other errors
	 */
	public static void marshalResponse(Object obj, OutputStream outputstream) {
		try {
			Writer writer = createWriter(outputstream);
			marshalResponse(obj, writer);
		} catch (UnsupportedEncodingException e) {
			//
		}
	}

	/**
	 * This method marshals a response and maps errors to an SOException.
	 * 
	 * @param obj response object 
	 * @param writer target writer
	 * @throws GenericErrorException on validation, marshal or other errors
	 */
	public static void marshalResponse(Object obj, Writer writer) {
		try {
			Marshaller marshaller = createMarshaller(obj.getClass());
			marshaller.marshal(obj, writer);
			writer.close();
		} catch (JAXBException e) {
			//
		} catch (IOException e) {
			//
		}
	}

	private static Unmarshaller createUnmarshaller(Class<?> klass) throws JAXBException {
		Unmarshaller unmarshaller = getJAXBContext(klass).createUnmarshaller();
		return unmarshaller;
	}

	/**
	 * Create an InputSource from an input stream
	 * @param xmlStream a XML input stream
	 * @return input source object taken by unmarshallers
	 * @throws IOException on IO errors, also encoding problems
	 */
	public static InputSource createInputSource(InputStream xmlStream) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(xmlStream);
		//String charSet = CastorHelper.getStreamEncoding(bis);
		String charSet = "";
		//if the character set name is not existing in the input, or is not
		// valid,
		// use the default character set name
		if (charSet == null || charSet.length() <= 0
				|| !Charset.isSupported(charSet))
			charSet = (new OutputStreamWriter(new ByteArrayOutputStream()))
					.getEncoding();
		Reader reader = new InputStreamReader(bis, charSet);
		return new InputSource(reader);
	}
	
	/**
	 * This method unmarshalls a castor object and takes the given charset in
	 * the XML preamble into account.
	 * 
	 * @param klass castor root class
	 * @param file source file
	 * @return instance of passed castor root class
	 * @throws GenericErrorException on validation, marshal or other errors
	 */
	public static Object unmarshal(Class<?> klass, File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return unmarshal(klass, fis);
		} catch (IOException e) {
			//
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					//
				}
			}
		}
		return fis;
	}

	/**
	 * This method unmarshalls a castor object and takes the given charset in
	 * the XML preamble into account.
	 * 
	 * @param klass castor root class
	 * @param bais source input stream
	 * @return instance of passed castor root class
	 * @throws GenericErrorException on validation, marshal or other errors
	 */
	public static Object unmarshal(Class<?> klass, InputStream bais) {
		long before = System.currentTimeMillis();
		try {
			InputSource is = createInputSource(bais);
			Object o = unmarshal(klass, is);
			long delta = System.currentTimeMillis() - before;
			return o;
		} catch (IOException e) {
			//
		}
		return before;
	}

	/**
	 * This method unmarshalls a JAXB object from an XML document string.
	 * 
	 * @param klass JAXB root class
	 * @param xmlStr XML document in a string
	 * @return instance of passed JAXB root class
	 * @throws GenericErrorException
	 */
	public static Object unmarshal(Class<?> klass, String xmlStr) {
		StringReader reader = new StringReader(xmlStr);
		return unmarshal(klass, new InputSource(reader));
	}
	
	/**
	 * This method unmarshalls a castor object and takes the given charset in
	 * the XML preamble into account.
	 * 
	 * @param klass castor root class
	 * @param is input source object
	 * @return instance of passed castor root class
	 * @throws SOException on validation, marshal or other errors
	 */
	private static Object unmarshal(Class<?> klass, InputSource is) {
		try {
			Unmarshaller unmarshaller = createUnmarshaller(klass);
			Object o = unmarshaller.unmarshal(is);
			return o;
		} catch (JAXBException e) {
			//
		}
		return is;
	}

}