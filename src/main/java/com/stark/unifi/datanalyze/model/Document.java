package com.stark.unifi.datanalyze.model;

import java.util.List;

import com.stark.unifi.datanalyze.analyzer.Syllabifier;

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

	public long getSentenceCount() {
		return sentences.size();
	}

	public long getWordCount() {
		return sentences.stream()
					.map(p -> p.getWords().size())
					.reduce(0, (s, i) -> i+s);
	}
	
	/**
	 * Words with more than 3 syllables
	 */
	public long getComplexWordCount(int numOfSyllables) {
		return sentences.stream()
					.flatMap(p -> p.getWords().stream())
					.filter(w -> new Syllabifier().getSyllableCount(w) > numOfSyllables)
					.count();
	}
	
	/**
	 * Words with more than 6 letters
	 */
	public long getLongWordCount() {
		return sentences.stream()
				.flatMap(p -> p.getWords().stream())
				.filter(w -> w.length() > 6)
				.count();
	}
	
	public long getSyllableCount() {
		return sentences.stream()
					.flatMap(p -> p.getWords().stream())
					.map(w -> new Syllabifier().getSyllableCount(w))
					.reduce(0, (s, i) -> i+s);
	}

	public long getCharacterCount() {
		return sentences.stream()
					.flatMap(p -> p.getWords().stream())
					.map(String::length)
					.reduce(0, (s, i) -> i+s);
	}

}
