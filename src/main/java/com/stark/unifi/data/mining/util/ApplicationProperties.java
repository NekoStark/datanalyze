package com.stark.unifi.data.mining.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.stark.unifi.data.mining.analyzer.TextAnalyzer;
import com.stark.unifi.data.mining.exception.PropertyLoadException;

public class ApplicationProperties {

	private static final Set<String> STOP_WORDS;
	private static final String PHRASE_STOP_REGEX;
	private static final String CHARACTER_REGEX;

	private ApplicationProperties() {
	}
	
	static {
		try {
			Properties properties = new Properties();
			properties.load(TextAnalyzer.class.getResourceAsStream("/application.properties"));

			// TODO caricare
			// TODO definire un default?
			STOP_WORDS = new HashSet<>();
			PHRASE_STOP_REGEX = properties.getProperty("phrase.stop.regex");
			CHARACTER_REGEX = properties.getProperty("character.regex");

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
	public static Set<String> getStopWords() {
		return STOP_WORDS;
	}

	/**
	 * Returns the regex that defines the characters that end a phrase.
	 * This regex is used to split the text in phrase units
	 * 
	 * @return the regex of end phrase characters
	 */
	public static String getPhraseStopRegex() {
		return PHRASE_STOP_REGEX;
	}

	/**
	 * Returns the regex that defines which caracters are to be filtered
	 * from a phrase before splitting into words
	 * 
	 * @return the regex of characters to be filtered
	 */
	public static String getCharacterRegex() {
		return CHARACTER_REGEX;
	}

}
