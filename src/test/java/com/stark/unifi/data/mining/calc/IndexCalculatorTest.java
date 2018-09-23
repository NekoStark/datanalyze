package com.stark.unifi.data.mining.calc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.stark.unifi.data.mining.analyzer.TextAnalyzer;
import com.stark.unifi.data.mining.model.Document;
import com.stark.unifi.data.mining.util.FileContentReader;

public class IndexCalculatorTest {

	private IndexCalculator calculator;
	private Document document;
	
	@Before
	public void setUp() throws Exception {
		String text = new FileContentReader().getText("/txt/basic_500.txt");
		document = new TextAnalyzer().analyzeText(text);
	}
	
	@Test
	public void testColemanLiauIndex() {
		calculator = new ColemanLiauIndexCalculator();
		BigDecimal result = calculator.execute(document);
		
		assertEquals(BigDecimal.valueOf(12.75), result);
	}
	
	@Test
	public void testAutomaticReadingIndex() {
		calculator = new AutomaticReadingIndexCalculator();
		BigDecimal result = calculator.execute(document);
		
		assertEquals(BigDecimal.valueOf(8.45), result);
	}
	
}
