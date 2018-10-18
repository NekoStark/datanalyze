package com.stark.unifi.datanalyze.exception;

public class CommandLineOptionsParseException extends RuntimeException {

	private static final long serialVersionUID = -4304032533107592253L;

	public CommandLineOptionsParseException(String message) {
		super(message);
	}

	public CommandLineOptionsParseException(Throwable t) {
		super(t);
	}

}
