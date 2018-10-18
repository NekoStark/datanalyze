package com.stark.unifi.datanalyze.exception;

public class TextExtractionException extends RuntimeException {

	private static final long serialVersionUID = -5534049879320741282L;

	public TextExtractionException(String message) {
		super(message);
	}
	
	public TextExtractionException(Throwable cause) {
		super(cause);
	}

}
