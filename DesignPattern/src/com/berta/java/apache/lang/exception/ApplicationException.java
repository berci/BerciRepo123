package com.berta.java.apache.lang.exception;

import org.apache.commons.lang.exception.NestableException;

public class ApplicationException extends NestableException {
	
	private static final long serialVersionUID = 1L;

	public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
