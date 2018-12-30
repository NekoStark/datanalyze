package com.stark.unifi.datanalyze.model;

import java.util.List;

public class Sentence {

	private List<String> words;
	private String originalText;

	public Sentence(List<String> words, String originalText) {
		this.words = words;
		this.originalText = originalText;
	}

	public List<String> getWords() {
		return words;
	}

	public String getOriginalText() {
		return originalText;
	}

	@Override
	public String toString() {
		return "<p>" + originalText + "<p><hr />";
	}

}
