package com.stark.unifi.datanalyze.model;

import java.math.BigDecimal;
import java.util.List;

public class Document {

	private List<Sentence> sentences;
	private String originalText;

	public Document(List<Sentence> phrases, String originalText) {
		this.sentences = phrases;
		this.originalText = originalText;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public String getOriginalText() {
		return originalText;
	}

	public BigDecimal getSentenceCount() {
		return BigDecimal.valueOf(sentences.size());
	}

	public BigDecimal getWordCount() {
		return BigDecimal.valueOf(
				sentences.stream()
					.map(p -> p.getWords().size())
					.reduce(0, (s, i) -> i+s)
				);
	}
	
	/**
	 * Words with more than 3 syllables
	 */
	public BigDecimal getComplexWordCount(int numOfSyllables) {
		return BigDecimal.valueOf(
				sentences.stream()
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
				sentences.stream()
				.flatMap(p -> p.getWords().stream())
				.filter(w -> w.getOriginalText().length() > 6)
				.count()
			);
	}
	
	public BigDecimal getSyllableCount() {
		return BigDecimal.valueOf(
				sentences.stream()
					.flatMap(p -> p.getWords().stream())
					.map(w -> w.getSyllables().size())
					.reduce(0, (s, i) -> i+s)
				);
	}

	public BigDecimal getCharacterCount() {
		return BigDecimal.valueOf(
				sentences.stream()
					.flatMap(p -> p.getWords().stream())
					.map(Word::getOriginalText)
					.map(String::length)
					.reduce(0, (s, i) -> i+s)
				);
	}

}
