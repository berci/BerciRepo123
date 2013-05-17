package com.berta.java.exception;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorsBundle {
	private static final String BUNDLE_NAME = "com.berta.java.exception.errors"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static final String CATEGORY = "test_berta";
	
	private ErrorsBundle() {
		//
	}
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String format(String key, int arg1) {
		return MessageFormat.format(getString(key), new Object[]{String.valueOf(arg1)});
	}
	
	public static String format(String key, String... args) {
		return MessageFormat.format(getString(key), args);
	}
	
	public static String format(String key, String arg1, String arg2) {
		return MessageFormat.format(getString(key), new Object[]{arg1, arg2});
	}
	
	public static ITestError create(String key, String... errorArgs) {
		TestError error = new TestError(CATEGORY, key, getString(key));
		for (String errorArg : errorArgs) {
			error.addErrorArgument(errorArg);
		}
		return error;
	}
	
	public static ITestError create(String key, ITestError cause, String... errorArgs) {
		TestError error = new TestError(CATEGORY, key, getString(key));
		error.setNestedError(cause);
		for (String errorArg : errorArgs) {
			error.addErrorArgument(errorArg);
		}
		return error;
	}
	
	public static ITestError create(String key, Throwable cause, String... errorArgs) {
		TestError error = new TestError(CATEGORY, key, getString(key));
		error.setNestedError(cause);
		for (String errorArg : errorArgs) {
			error.addErrorArgument(errorArg);
		}
		return error;
	}
}
