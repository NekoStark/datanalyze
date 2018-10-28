package com.stark.unifi.datanalyze.calc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.stark.unifi.datanalyze.model.Document;

public class IndexCalculatorTest {

	private IndexCalculator calculator;
	private Document document;
	
	@Before
	public void setUp() throws Exception {
		document = mock(Document.class);
		when(document.getSentenceCount()).thenReturn(60L);
		when(document.getWordCount()).thenReturn(500L);
		when(document.getLongWordCount()).thenReturn(153L);
		when(document.getComplexWordCount(2)).thenReturn(381L);
		when(document.getComplexWordCount(3)).thenReturn(200L);
		when(document.getSyllableCount()).thenReturn(1738L);
		when(document.getCharacterCount()).thenReturn(2730L);
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
