package com.stark.unifi.datanalyze.model;

import java.util.List;

public class Word {

	private List<String> syllables;
	private String originalText;

	public Word(List<String> syllables, String originalText) {
		super();
		this.syllables = syllables;
		this.originalText = originalText;
	}

	public List<String> getSyllables() {
		return syllables;
	}

	public String getOriginalText() {
		return originalText;
	}

}
