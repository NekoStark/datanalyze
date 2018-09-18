package com.stark.unifi.data.mining.model;

import java.util.List;

public class Phrase {

	private List<String> words;
	private String originalText;

	public Phrase(List<String> words, String originalText) {
		this.words = words;
		this.originalText = originalText;
	}

	public List<String> getWords() {
		return words;
	}

	public String getOriginalText() {
		return originalText;
	}

}
