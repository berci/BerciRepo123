package com.berta.java.apache.lang.exception;

import org.apache.commons.lang.exception.ExceptionUtils;

public class MainClass {
	public static void main(String[] args) {
        try {
            a();
        } catch (Exception e) {
            System.out.println(
                "Number of Throwable objects in the exception chain is: " +
                ExceptionUtils.getThrowableCount(e));
            System.out.println("-------------");
            System.out.println(ExceptionUtils.getRootCauseMessage(e));
            System.out.println("-------------");
            System.out.println(ExceptionUtils.getMessage(e));
            System.out.println("-------------");
            System.out.println(ExceptionUtils.getCause(e));
            System.out.println("-------------");
            System.out.println(ExceptionUtils.getRootCause(e));
            System.out.println("-------------");
            e.printStackTrace();
        }
    }

    public static void a() throws Exception {
        try {
            b();
        } catch (Exception e) {
            throw new ApplicationException("Packaged into Nestable", e);
        }
    }

    public static void b() throws Exception {
        throw new Exception("The Root Exception");
    }
}
