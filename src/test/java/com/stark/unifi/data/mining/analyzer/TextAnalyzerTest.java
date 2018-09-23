package com.stark.unifi.data.mining.analyzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.stark.unifi.data.mining.model.Document;
import com.stark.unifi.data.mining.util.FileContentReader;

public class TextAnalyzerTest {

	private TextAnalyzer analyzer;
	
	@Test
	public void testAnalyzeTextBasic() throws Exception {
		analyzer = new TextAnalyzer();
		String text = new FileContentReader().getText("/txt/basic.txt");
		Document result = analyzer.analyzeText(text);
		
		assertEquals(5L, result.getPhraseCount());
		assertEquals(36L, result.getWordCount());
		assertEquals(205L, result.getCharacterCount());
	}
	
	//TODO test per altra lingua
	
}
