package com.berta.java.exception;

public class TestErrorException extends Exception implements ITestErrorException {
	
	private static final long serialVersionUID = 1L;
	
	protected ITestError testError;
	
	public TestErrorException(ITestError testError) {
		this.testError = testError;
	}
	
	@Override
	public ITestError getTestError() {
		return testError;
	}
	
	@Override
	public String toString() {
		return testError.toString();
	}
	
	@Override
	public String getMessage() {
		if(testError.getErrorArguments().size() == 0) {
			return testError.getUnformattedMessage();
		}
		
		StringBuilder sb = new StringBuilder(testError.getUnformattedMessage());
		sb.append(" [");
		for(String arg : testError.getErrorArguments()) {
			sb.append("\"").append(arg).append("\"");
		}
		sb.append(" ]");
		return sb.toString();
	}

}
