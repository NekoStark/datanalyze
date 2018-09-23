package com.stark.unifi.data.mining.util;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.stark.unifi.data.mining.analyzer.TextAnalyzer;
import com.stark.unifi.data.mining.exception.PropertyLoadException;

public class ApplicationProperties {

	private Set<String> stopWords;
	private String phraseStopRegex;
	private String characterRegex;

	public ApplicationProperties(String propertyFilePath) {
		try {
			Properties properties = new Properties();
			properties.load(TextAnalyzer.class.getResourceAsStream(propertyFilePath));

			stopWords = initStopWords(properties.getProperty("stopwords"));
			phraseStopRegex = properties.getProperty("phrase.stop.regex");
			characterRegex = properties.getProperty("character.regex");

		} catch (IOException e) {
			throw new PropertyLoadException("Error during load of application properties", e);

		}
	}
	
	/**
	 * Returns the set of words which are filtered when parsing text for word count
	 * These words are defined in the application properties, separated by comma
	 * 
	 * @return set of stop-words
	 */
	public Set<String> getStopWords() {
		return stopWords;
	}

	/**
	 * Returns the regex that defines the characters that end a phrase.
	 * This regex is used to split the text in phrase units
	 * 
	 * @return the regex of end phrase characters
	 */
	public String getPhraseStopRegex() {
		return phraseStopRegex;
	}

	/**
	 * Returns the regex that defines which caracters are to be filtered
	 * from a phrase before splitting into words
	 * 
	 * @return the regex of characters to be filtered
	 */
	public String getCharacterRegex() {
		return characterRegex;
	}
	
	private Set<String> initStopWords(String property) {
		return Stream.of(property.split(","))
				.map(String::trim)
				.collect(Collectors.toSet());
	}

}
