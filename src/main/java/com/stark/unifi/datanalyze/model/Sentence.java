package com.stark.unifi.datanalyze.model;

import java.util.List;

public class Sentence {

	private List<Word> words;
	private String originalText;

	public Sentence(List<Word> words, String originalText) {
		this.words = words;
		this.originalText = originalText;
	}

	public List<Word> getWords() {
		return words;
	}

	public String getOriginalText() {
		return originalText;
	}

}
