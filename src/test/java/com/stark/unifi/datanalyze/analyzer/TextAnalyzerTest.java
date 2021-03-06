package com.stark.unifi.datanalyze.analyzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.util.FileContentReader;

public class TextAnalyzerTest {

	private TextAnalyzer analyzer;
	
	@Test
	public void testAnalyzeTextBasic() throws Exception {
		analyzer = new TextAnalyzer();
		String text = new FileContentReader().read("/txt/basic.txt");
		Document result = analyzer.analyzeText(text);
		
		assertEquals(text, result.getOriginalText());
		
		assertEquals(6, result.getSentences().size());
		assertEquals(6, result.getSentenceCount());
		
		assertEquals(36, result.getWordCount());
		assertEquals(244, result.getCharacterCount());
		assertEquals(80, result.getSyllableCount());
		assertEquals(5, result.getComplexWordCount(3));
		assertEquals(12, result.getLongWordCount());
	}
	
	@Test
	public void testAnalyzeTextWithAccents() throws Exception {
		analyzer = new TextAnalyzer();
		String text = new FileContentReader().read("/txt/accents.txt");
		Document result = analyzer.analyzeText(text);
		
		assertEquals(text, result.getOriginalText());
		
		assertEquals(5, result.getWordCount());
		assertEquals(38, result.getCharacterCount());
		assertEquals(12, result.getSyllableCount());
	}
	
}
