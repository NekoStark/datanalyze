package com.stark.unifi.datanalyzer.analyzer;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.stark.unifi.datanalyze.analyzer.TextAnalyzer;
import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.util.FileContentReader;

public class TextAnalyzerTest {

	private TextAnalyzer analyzer;
	
	@Test
	public void testAnalyzeTextBasic() throws Exception {
		analyzer = new TextAnalyzer();
		String text = new FileContentReader().getText("/txt/basic.txt");
		Document result = analyzer.analyzeText(text);
		
		assertEquals(BigDecimal.valueOf(6), result.getPhraseCount());
		assertEquals(BigDecimal.valueOf(36), result.getWordCount());
		assertEquals(BigDecimal.valueOf(205), result.getCharacterCount());
	}
	
	//TODO test per altra lingua
	
}
