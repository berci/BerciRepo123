package com.berta.java.exception;

public class TestError extends AbstractTestError {
	
	public TestError(String category, String aplerr, String standardErrorText) {
		super(category, standardErrorText);
		setAplerr(aplerr);
	}
	
	protected TestError(String source, String message) {
		super(source, message);
	}
	
	public TestError(String category, Throwable cause) {
		this(category, null, cause);
	}
	
	public TestError(String category, String message, Throwable cause) {
		super(category, message != null ? message : cause.toString());
		setCallStack(cause);
	}
}
