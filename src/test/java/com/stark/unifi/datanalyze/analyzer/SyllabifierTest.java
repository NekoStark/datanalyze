package com.stark.unifi.datanalyze.analyzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SyllabifierTest {

	@Test
	public void testSyllabify1() {
		// vocale iniziale seguita da una sola consonante
		assertSyllables("elaborare", 5);
		assertSyllables("aliante", 3);
		assertSyllables("umido", 3);
		assertSyllables("idolo", 3);
		assertSyllables("odore", 3);
		assertSyllables("uno", 2);
	}
		
	@Test
	public void testSyllabify2() {
		// consonante semplice + vocale seguente
		assertSyllables("dado", 2);
		assertSyllables("pera", 2);
	}
	
	@Test
	public void testSyllabify3() {
		// gruppo di consonante + l/r
		assertSyllables("atletica", 4);
		assertSyllables("biblico", 3);
		assertSyllables("brodo", 2);
		assertSyllables("impianto", 3);
		assertSyllables("inclito", 3);
		assertSyllables("credere", 3);
		assertSyllables("dromedario", 4);
		assertSyllables("flebile", 3);
		assertSyllables("africano", 4);
		assertSyllables("greco", 2);
		assertSyllables("prego", 2);
		assertSyllables("treno", 2);
	}
	
	@Test
	public void testSyllabify4() {
		// s + consonante/i
		assertSyllables("ostracismo", 4);
		assertSyllables("teschio", 2);
		assertSyllables("costola", 3);
		assertSyllables("scoiattolo", 4);
		assertSyllables("caspita", 3);
		assertSyllables("striscione", 3);
	}
	
	@Test
	public void testSyllabify5() {
		// consonanti uguali, cq
		assertSyllables("tetto", 2);
		assertSyllables("acqua", 2);
		assertSyllables("risciacquo", 3);
		assertSyllables("calma", 2);
		assertSyllables("ricerca", 3);
		assertSyllables("rabdomante", 4);
		assertSyllables("imbuto", 3);
		assertSyllables("caldo", 2);
		assertSyllables("ingegnere", 4);
		assertSyllables("quando", 2);
		assertSyllables("tecnico", 3);
		assertSyllables("aritmetica", 5);
	}
	
	@Test
	public void testSyllabifyDiphthong() {
		assertSyllables("auguri", 3);
		assertSyllables("auto", 2);
		assertSyllables("viola", 2);
		assertSyllables("indiano", 3);
		assertSyllables("piolo", 2);
		assertSyllables("lingua", 2);
		assertSyllables("questo", 2);
		assertSyllables("zaino", 2);
	}
	
	@Test
	public void testSyllabifyHyatus() {
		assertSyllablesHyatus("poeta", 3);
		assertSyllablesHyatus("paesaggio", 5);
		assertSyllablesHyatus("gladiolo", 4);
		assertSyllablesHyatus("costruire", 4);
		assertSyllablesHyatus("amnistia", 4);
	}
	
	private void assertSyllables(String word, Integer num) {
		assertEquals(num, new Syllabifier(true).getSyllableCount(word));
	}
	
	private void assertSyllablesHyatus(String word, Integer num) {
		assertEquals(num, new Syllabifier(false).getSyllableCount(word));
	}
	
}
