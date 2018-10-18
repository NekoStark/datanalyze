package com.stark.unifi.datanalyze.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import com.stark.unifi.datanalyze.exception.PropertyLoadException;

public class ApplicationProperties {

	private Set<String> stopWords;
	private String phraseStopRegex;
	private String characterRegex;

	public ApplicationProperties(String propertyFilePath) {
		try {
			Properties properties = new Properties();
			properties.load(ApplicationProperties.class.getResourceAsStream(propertyFilePath));

			stopWords = initStopWords(properties.getProperty("stopwords.file"));
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
	
	private Set<String> initStopWords(String property) throws IOException {
		String stopWordsFilePath = ApplicationProperties.class.getResource(property).getPath();
		return Files
				.readAllLines(Paths.get(stopWordsFilePath))
				.stream()
				.map(String::trim)
				.collect(Collectors.toSet());
	}

}
