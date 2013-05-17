package com.berta.java.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITestError {
	String APLERR = "aplerr";
	String ERRCOD = "errocd";
	
	/**
	 * @return source of error, e.g. module or component
	 */
	String getSource();
	
	/**
	 * @param source source of error
	 */
	void setSource(String source);
	
	/**
	 * @return timestamp of error occurrence
	 */
	Date getOccurredAt();
	
	/**
	 * @return unformatted message as fallback if i18n fails
	 */
	String getUnformattedMessage();
	
	/**
	 * Return all error codes - some systems have multiple errors - as a Map.
	 * This method must not return null.
	 * @return error codes map
	 */
	Map<String, String> getErrorCodes();
	
	/**
	 * This method returns the error arguments.
	 * 
	 * They specify the error in more detail and are well-defined for a certain
	 * error code. Typically they are used to provide a user-friendly localized error message.
	 * 
	 * Note: this method shall not return null
	 * 
	 * @return list of error arguments
	 */
	List<String> getErrorArguments();
	
	/**
	 * @return error call stack information, typically Java stack
	 */
	String getCallStack();
	
	/**
	 * @param th set call stack by passing a Java exception
	 */
	void setCallStack(Throwable th);

	/**
	 * @return nested error
	 */
	ITestError getNestedError();
	
	/**
	 * @param error set nested error directly
	 */
	void setNestedError(ITestError error);
	
	/**
	 * @param th set nested error by Java Throwable
	 */
	void setNestedError(Throwable th);
	
	/**
	 * @return sibling error (e.g. one error in try, catch and finally)
	 */
	List<ITestError> getSiblingErrors();
	
	/**
	 * @param error add a sibling error directly
	 */
	void addSiblingError(ITestError error);
	
	/**
	 * @param th add an sibling error by Java Throwable
	 */
	void addSiblingError(Throwable th);
	
	/**
	 * @return a stringified summary
	 */
	String getSummary();
	
}
