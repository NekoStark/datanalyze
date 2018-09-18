package com.stark.unifi.data.mining.model;

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

	public long getPhraseCount() {
		return phrases.size();
	}

	public long getWordCount() {
		return phrases.stream()
					.map(p -> p.getWords().size())
					.reduce(0, (s, i) -> i+s);
	}

	public long getCharacterCount() {
		return phrases.stream()
					.flatMap(p -> p.getWords().stream())
					.map(String::length)
					.reduce(0, (s, i) -> i+s);
	}

}
