package com.stark.unifi.data.mining.analyzer;

import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.stark.unifi.data.mining.model.Document;

public class TextAnalyzerTest {

	private String text;
	
	@Before
	public void setUp() throws Exception {
		StringBuilder sb = new StringBuilder();
		String fileName = TextAnalyzerTest.class.getResource("/txt/basic.txt").getPath();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(s -> sb.append(s));
		}
		
		text = sb.toString();
	}
	
	@Test
	public void testAnalyzeText() {
		Document result = TextAnalyzer.analyzeText(text);
		
		assertEquals(6L, result.getPhraseCount());
		assertEquals(36L, result.getWordCount());
		assertEquals(205L, result.getCharacterCount());
	}
	
}
