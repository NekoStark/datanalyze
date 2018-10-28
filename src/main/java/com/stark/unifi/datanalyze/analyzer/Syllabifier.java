package com.stark.unifi.datanalyze.analyzer;

import java.util.HashSet;
import java.util.Set;

public class Syllabifier {

	private static final Set<Character> VOWELS;
	private boolean alwaysTreatAsDiphthong;

	static {
		VOWELS = new HashSet<>();
		VOWELS.add('a');
		VOWELS.add('e');
		VOWELS.add('i');
		VOWELS.add('o');
		VOWELS.add('u');
	}

	public Syllabifier() {
		this(false);
	}

	public Syllabifier(boolean alwaysTreatAsDiphthong) {
		this.alwaysTreatAsDiphthong = alwaysTreatAsDiphthong;
	}

	public Integer getSyllableCount(String word) {
		Integer result = 0;
		Integer consecuteVowels = 0;

		for (Character c : word.toCharArray()) {
			if (isVowel(c)) {
				// se non è un dittongo, oppure se è un dittongo 
				// ma non sto trattando tutto come dittongo, 
				// allora conto la vocale
				if (consecuteVowels != 1 || !alwaysTreatAsDiphthong) {
					result++;
				}
				
				consecuteVowels++;

			} else {
				consecuteVowels = 0;

			}

		}

		return result;
	}

	private boolean isVowel(Character c) {
		if (c == null) {
			return false;
		}
		return VOWELS.contains(c);
	}

}
