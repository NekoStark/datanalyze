package com.stark.unifi.datanalyze.calc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.stark.unifi.datanalyze.analyzer.TextAnalyzer;
import com.stark.unifi.datanalyze.calc.AutomatedReadabilityIndexCalculator;
import com.stark.unifi.datanalyze.calc.ColemanLiauIndexCalculator;
import com.stark.unifi.datanalyze.calc.IndexCalculator;
import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.util.FileContentReader;

public class IndexCalculatorTest {

	private IndexCalculator calculator;
	private Document document;
	
	@Before
	public void setUp() throws Exception {
		String text = new FileContentReader().read("/txt/basic_500.txt");
		document = new TextAnalyzer().analyzeText(text);
	}
	
	@Test
	public void testFleschReadingEase() {
		calculator = new FleschReadingEaseCalculator();
		BigDecimal result = calculator.execute(document);
		
		assertEquals(BigDecimal.valueOf(-95.69), result);
	}
	
	@Test
	public void testFogIndex() {
		calculator = new FogIndexCalculator();
		BigDecimal result = calculator.execute(document);
		
		assertEquals(BigDecimal.valueOf(19.33), result);
	}
	
	@Test
	public void testColemanLiauIndex() {
		calculator = new ColemanLiauIndexCalculator();
		BigDecimal result = calculator.execute(document);
		
		assertEquals(BigDecimal.valueOf(12.75), result);
	}
	
	@Test
	public void testAutomatedReadabilityIndex() {
		calculator = new AutomatedReadabilityIndexCalculator();
		BigDecimal result = calculator.execute(document);
		
		assertEquals(BigDecimal.valueOf(8.45), result);
	}
	
}
