package com.berci.parser;

import org.apache.commons.lang3.exception.ContextedException;

import com.berci.parser.ParserUtils.ParserEnum;

public class ParseException extends ContextedException {

	private static final long serialVersionUID = 1L;

	private ParseException(Throwable t) {
		super(t);
	}
	
	public static ParseException createParseException(Throwable t, ParserEnum parserEnum, String line) {
		return (ParseException) new ParseException(t)
		.addContextValue("Column", parserEnum.name)
		.addContextValue("Pos", parserEnum.pos)
		.addContextValue("Line", line);
	}

	public static ParseException createParseException(Throwable t, String path) {
		return (ParseException) new ParseException(t).addContextValue("Path", path);
	}
	
}
