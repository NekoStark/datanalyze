package com.stark.unifi.datanalyze.exception;

public class ResultsWriterException extends RuntimeException {

	private static final long serialVersionUID = -7390702690134538891L;

	public ResultsWriterException(String message) {
		super(message);
	}

	public ResultsWriterException(Throwable cause) {
		super(cause);
	}

}
