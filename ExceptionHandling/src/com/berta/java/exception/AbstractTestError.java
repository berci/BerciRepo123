package com.berta.java.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractTestError implements ITestError {

	private String source;
	private String unformattedMessage;
	private Date occurredAt;
	private String callStack;
	private Map<String, String> errorCodes;
	private List<String> errorArguments;
	private ITestError nestedError;
	private List<ITestError> siblingErrors;
	
	protected AbstractTestError(String source, String unformattedMessage) {
		this.source = source;
		this.unformattedMessage = unformattedMessage;
		this.occurredAt = new Date();
		this.errorCodes = new HashMap<String, String>();
		this.errorArguments = new ArrayList<String>();
		this.siblingErrors = new ArrayList<ITestError>();
	}
	
	@Override
	public void addSiblingError(ITestError error) {
		siblingErrors.add(error);
	}

	@Override
	public void addSiblingError(Throwable th) {
		siblingErrors.add(createError(th));
	}

	@Override
	public String getCallStack() {
		return callStack;
	}

	@Override
	public List<String> getErrorArguments() {
		return Collections.unmodifiableList(errorArguments);
	}

	@Override
	public Map<String, String> getErrorCodes() {
		return Collections.unmodifiableMap(errorCodes);
	}

	@Override
	public ITestError getNestedError() {
		return nestedError;
	}

	@Override
	public Date getOccurredAt() {
		return occurredAt;
	}

	@Override
	public List<ITestError> getSiblingErrors() {
		return Collections.unmodifiableList(siblingErrors);
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public String getSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getSource()).append("] ");
		sb.append(getUnformattedMessage());
		if (getErrorArguments().size() > 0) {
			sb.append("( ");
			for (String errorArgument : getErrorArguments()) {
				sb.append("'").append(errorArgument).append("' ");
			}
			sb.append(" )");
		}
		if (getNestedError() != null) {
			sb.append("\n").append(getNestedError().getSummary());
		}
		return sb.toString();
	}

	@Override
	public String getUnformattedMessage() {
		return unformattedMessage;
	}

	@Override
	public void setCallStack(Throwable th) {
		if (th == null) {
			return;
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		this.callStack = sw.toString();
	}
	
	public void setCallStack(String callStack) {
		this.callStack = callStack;
	}
	
	public void setAplerr(String aplerr) {
		addErrorCode(APLERR, aplerr);
	}
	
	protected void setErrcod(String errcod) {
		addErrorCode(ERRCOD, errcod);
	}
	
	public void addErrorCode(String errorName, String errorCode) {
		errorCodes.put(errorName, errorCode);
	}

	@Override
	public void setNestedError(ITestError nestedError) {
		this.nestedError = nestedError;
	}

	@Override
	public void setNestedError(Throwable th) {
		if (th != null) {
			setNestedError(createError(th));
		}
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}
	
	public void addErrorArgument(String errorArg) {
		errorArguments.add(errorArg);
	}
	
	public void setOccurredAt(Date occurredAt) {
		this.occurredAt = occurredAt;
	}
	
	private ITestError createError(Throwable th) {
		if (th instanceof ITestErrorException)
			return ((ITestErrorException) th).getTestError();
		return new TestError(getSource(), th);
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[").append(getSource()).append("] ");
		buf.append(getUnformattedMessage());

		if (getErrorCodes().size() > 0) {
			buf.append("\nError Codes....: ").append(getErrorCodes());
		}
		if (getErrorArguments().size() > 0) {
			buf.append("\nError Arguments: ");
			for (String errorArgument : getErrorArguments()) {
				buf.append("'").append(errorArgument).append("' ");
			}
		}
		if (getCallStack() != null) {
			buf.append("\nCall Stack.....: ").append(getCallStack());
		}
		if (getNestedError() != null) {
			buf.append("\nNested Error...:").append(getNestedError());
		}
		buf.append("\n");
		return buf.toString();
	}

}
