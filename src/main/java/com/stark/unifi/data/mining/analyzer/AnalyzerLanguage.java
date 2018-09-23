package com.stark.unifi.data.mining.analyzer;

public enum AnalyzerLanguage {

	DEFAULT("/application.properties"),
	ENGLISH("/application.properties"),
	ITALIAN("/application_it.properties");
	
	private String propertyFilePath;
	
	private AnalyzerLanguage(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;
	}
	
	public String getPropertyFilePath() {
		return propertyFilePath;
	}
	
}
