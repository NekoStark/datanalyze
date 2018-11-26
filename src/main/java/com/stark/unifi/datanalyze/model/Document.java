package com.stark.unifi.datanalyze.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import eu.crydee.syllablecounter.SyllableCounter;

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
					.filter(w -> getSyllableCount(w) > numOfSyllables)
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
					.map(Document::getSyllableCount)
					.reduce(0, (s, i) -> i+s);
	}

	// XXX V5
	public long getCharacterCount() {
		return sentences.stream()
					.map(Sentence::getOriginalText)
					.map(StringUtils::stripAccents)
					.map(p -> p.replaceAll("[^a-zA-Z_0-9]+", " "))
					.map(String::toLowerCase)
					.filter(s -> s.length() > 1)
					.map(String::length)
					.reduce(0, (s, i) -> i+s);
				
//		return sentences.stream()
//					.flatMap(p -> p.getWords().stream())
//					.map(String::length)
//					.reduce(0, (s, i) -> i+s);
	}
	
	private static Integer getSyllableCount(String w) {
		return new SyllableCounter().count(w);
//		return new Syllabifier().getSyllableCount(w);
	}

	@Override
	public String toString() {
		return "Document [" + originalText + "]";
	}

}
