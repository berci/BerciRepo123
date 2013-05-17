/*
 * Created on Mar 21, 2005
 */
package com.berta.java.exception;

public enum Errors {

	// Very generic errors
	UNEXPECTED_EXCEPTION("An unexpected exception happened."),
	UNEXPECTED_ERROR("An unexpected error happened.", "explanation"),
	CONFIG_ERROR("Error in configuration.", "paramName", "explanation");
	
	public static final String CATEGORY = "test_berta";
	
	private String text;
	private String[] args;

	private Errors(String text, String ... args) {
		this.text = text;
		this.args = args;
	}

	public ITestError create(String... errorArgs) {
		TestError error = new TestError(CATEGORY, name(), text);
		for (String errorArg : errorArgs) {
			error.addErrorArgument(errorArg);
		}
		return error;
	}
	
	public ITestError create(ITestError cause, String... errorArgs) {
		TestError error = new TestError(CATEGORY, name(), text);
		error.setNestedError(cause);
		for (String errorArg : errorArgs) {
			error.addErrorArgument(errorArg);
		}
		return error;
	}
	
	public ITestError create(Throwable cause, String... errorArgs) {
		TestError error = new TestError(CATEGORY, name(), text);
		error.setNestedError(cause);
		for (String errorArg : errorArgs) {
			error.addErrorArgument(errorArg);
		}
		return error;
	}
	
	public boolean isError(ITestError error) {
		return name().equals(error.getErrorCodes().get(ITestError.APLERR));
	}
	
}
