package com.stark.unifi.datanalyze.calc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.stark.unifi.datanalyze.analyzer.TextAnalyzer;
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
		assertEquals(-95.69, calculator.execute(document), 0.01);
	}
	
	@Test
	public void testFogIndex() {
		calculator = new FogIndexCalculator();
		assertEquals(19.33, calculator.execute(document), 0.01);
	}
	
	@Test
	public void testColemanLiauIndex() {
		calculator = new ColemanLiauIndexCalculator();
		assertEquals(12.75, calculator.execute(document), 0.01);
	}
	
	@Test
	public void testLixIndex() {
		calculator = new LixIndexCalculator();
		assertEquals(38.93, calculator.execute(document), 0.01);
	}
	
	@Test
	public void testAutomatedReadabilityIndex() {
		calculator = new AutomatedReadabilityIndexCalculator();
		assertEquals(8.45, calculator.execute(document), 0.01);
	}
	
	@Test
	public void testSmogIndex() {
		calculator = new SmogIndexCalculator();
		assertEquals(17.52, calculator.execute(document), 0.01);
	}
	
	@Test
	public void testFleschKincaidIndex() {
		calculator = new FleschKincaidIndexCalculator();
		assertEquals(28.68, calculator.execute(document), 0.01);
	}
	
}
