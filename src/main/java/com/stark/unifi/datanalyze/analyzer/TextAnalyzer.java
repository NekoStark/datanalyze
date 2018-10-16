package com.stark.unifi.datanalyze.analyzer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.model.Phrase;
import com.stark.unifi.datanalyze.util.ApplicationProperties;

public class TextAnalyzer {

	private ApplicationProperties properties;
	
	public TextAnalyzer() {
		this("/application.properties");
	}
	
	public TextAnalyzer(String propertyFilePath) {
		this.properties = new ApplicationProperties(propertyFilePath);
	}
	
	public Document analyzeText(String text) {
		return new Document(getPhrases(text), text);
	}
	
	private List<Phrase> getPhrases(String text) {
		return Stream.of(text.split(properties.getPhraseStopRegex()))
					.map(String::trim)
					.map(s -> new Phrase(getWords(s), s))
					.collect(Collectors.toList());
	}
	
	private List<String> getWords(String text) {
		String sanitized = text.replaceAll(properties.getCharacterRegex(), " ");
		return Stream.of(sanitized.split("\\s"))
					.filter(s -> !properties.getStopWords().contains(s))
					.collect(Collectors.toList());
	}
	
}
