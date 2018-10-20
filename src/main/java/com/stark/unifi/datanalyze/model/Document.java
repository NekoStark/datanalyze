package com.stark.unifi.datanalyze.model;

import java.math.BigDecimal;
import java.util.List;

public class Document {

	private List<Phrase> phrases;
	private String originalText;

	public Document(List<Phrase> phrases, String originalText) {
		this.phrases = phrases;
		this.originalText = originalText;
	}

	public List<Phrase> getPhrases() {
		return phrases;
	}

	public String getOriginalText() {
		return originalText;
	}

	public BigDecimal getPhraseCount() {
		return BigDecimal.valueOf(phrases.size());
	}

	public BigDecimal getWordCount() {
		return BigDecimal.valueOf(
				phrases.stream()
					.map(p -> p.getWords().size())
					.reduce(0, (s, i) -> i+s)
				);
	}
	
	/**
	 * Words with more than 3 syllables
	 */
	public BigDecimal getComplexWordCount(int numOfSyllables) {
		return BigDecimal.valueOf(
				phrases.stream()
					.flatMap(p -> p.getWords().stream())
					.filter(w -> w.getSyllables().size() > numOfSyllables)
					.count()
				);
	}
	
	/**
	 * Words with more than 6 letters
	 */
	public BigDecimal getLongWordCount() {
		return BigDecimal.valueOf(
				phrases.stream()
				.flatMap(p -> p.getWords().stream())
				.filter(w -> w.getOriginalText().length() > 6)
				.count()
			);
	}
	
	public BigDecimal getSyllableCount() {
		return BigDecimal.valueOf(
				phrases.stream()
					.flatMap(p -> p.getWords().stream())
					.map(w -> w.getSyllables().size())
					.reduce(0, (s, i) -> i+s)
				);
	}

	public BigDecimal getCharacterCount() {
		return BigDecimal.valueOf(
				phrases.stream()
					.flatMap(p -> p.getWords().stream())
					.map(Word::getOriginalText)
					.map(String::length)
					.reduce(0, (s, i) -> i+s)
				);
	}

}
