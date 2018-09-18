package com.stark.unifi.data.mining.analyzer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stark.unifi.data.mining.model.Document;
import com.stark.unifi.data.mining.model.Phrase;
import com.stark.unifi.data.mining.util.ApplicationProperties;

public class TextAnalyzer {

	private TextAnalyzer() {
	}
	
	public static Document analyzeText(String text) {
		return new Document(getPhrases(text), text);
	}
	
	private static  List<Phrase> getPhrases(String text) {
		return Stream.of(text.split(ApplicationProperties.getPhraseStopRegex()))
					.map(String::trim)
					.map(s -> new Phrase(getWords(s), s))
					.collect(Collectors.toList());
	}
	
	private static  List<String> getWords(String text) {
		// FIXME mettere una regex che non becchi gli underscore
		// TODO rimuovere stopwords
		String sanitized = text.replaceAll(ApplicationProperties.getCharacterRegex(), " ");
		return Arrays.asList(sanitized.split("\\s"));
	}
	
}
