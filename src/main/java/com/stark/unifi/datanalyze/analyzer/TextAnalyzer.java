package com.stark.unifi.datanalyze.analyzer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.model.Sentence;
import com.stark.unifi.datanalyze.model.Word;
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
	
	private List<Sentence> getPhrases(String text) {
		return Stream.of(text.split(properties.getPhraseStopRegex()))
					.map(String::trim)
					.map(s -> new Sentence(getWords(s), s))
					.collect(Collectors.toList());
	}
	
	private List<Word> getWords(String phrase) {
		String sanitized = phrase.replaceAll(properties.getCharacterRegex(), " ");
		return Stream.of(sanitized.split("\\s"))
					.filter(s -> !properties.getStopWords().contains(s))
					.map(w -> new Word(getSyllables(w), w))
					.collect(Collectors.toList());
	}
	
	private List<String> getSyllables(String word) {
		return new Syllabifier().getSyllables(word);
	}
	
}
