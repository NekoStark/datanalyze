package com.stark.unifi.datanalyzer.analyzer;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.stark.unifi.datanalyze.analyzer.Syllabifier;

public class SyllabifierTest {

	@Test
	public void testSyllabify() {
		assertSyllables("elaborare", "e","la","bo","ra","re");
		assertSyllables("aliante", "a","lian","te");
		assertSyllables("umido", "u","mi","do");
		assertSyllables("idolo", "i","do","lo");
		assertSyllables("odore", "o","do","re");
		assertSyllables("uno", "u","no");
	}
	
	private void assertSyllables(String word, String...syllables) {
		assertEquals(Arrays.asList(syllables), new Syllabifier(true).getSyllables(word));
	}
	
}
