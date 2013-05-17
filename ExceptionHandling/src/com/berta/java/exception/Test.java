package com.berta.java.exception;

public class Test {

	public static void main(String[] args) throws TestErrorException {
		
		try {
			Integer.parseInt("ss");
		} catch (Exception e) {
			// ITestError error = Errors.CONFIG_ERROR.create(e, "sasasa", "wewew", "32323");
			
			ITestError error = ErrorsBundle.create("TEST_EXCEPTION", e, "testbundle");
			throw new TestErrorException(error);
		}
		
	}

}
