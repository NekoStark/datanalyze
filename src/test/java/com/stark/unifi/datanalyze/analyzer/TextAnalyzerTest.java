package com.stark.unifi.datanalyze.analyzer;

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
		String text = new FileContentReader().read("/txt/basic.txt");
		Document result = analyzer.analyzeText(text);
		
		assertEquals(BigDecimal.valueOf(6), result.getPhraseCount());
		assertEquals(BigDecimal.valueOf(36), result.getWordCount());
		assertEquals(BigDecimal.valueOf(205), result.getCharacterCount());
		assertEquals(BigDecimal.valueOf(137), result.getSyllableCount());
		assertEquals(BigDecimal.valueOf(17), result.getComplexWordCount());
		assertEquals(BigDecimal.valueOf(12), result.getLongWordCount());
	}
	
}
