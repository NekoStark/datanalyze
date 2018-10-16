package com.stark.unifi.datanalyze.analyzer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Syllabifier {

	private static final Set<Character> VOWELS;
	private static final Set<Character> GROUP_CONSONANT;
	private List<String> result;
	private boolean alwaysTreatAsDiphthong;

	static {
		VOWELS = new HashSet<>();
		VOWELS.add('a');
		VOWELS.add('e');
		VOWELS.add('i');
		VOWELS.add('o');
		VOWELS.add('u');

		GROUP_CONSONANT = new HashSet<>();
		GROUP_CONSONANT.add('b');
		GROUP_CONSONANT.add('c');
		GROUP_CONSONANT.add('d');
		GROUP_CONSONANT.add('f');
		GROUP_CONSONANT.add('g');
		GROUP_CONSONANT.add('p');
		GROUP_CONSONANT.add('t');
	}

	public Syllabifier() {
		this(false);
	}
	
	public Syllabifier(boolean alwaysTreatAsDiphthong) {
		this.alwaysTreatAsDiphthong = alwaysTreatAsDiphthong;
		result = new LinkedList<>();
	}

	public List<String> getSyllables(String word) {
		List<Character> stack = new LinkedList<>();
		boolean doppiaVocale = false;
		
		for(char c : word.toCharArray()) {
			if(stack.isEmpty()) {
				// nulla
			} else {
				// vocale seguita da consonante
				Character last = last(stack);
				if(isConsonante(c) && isVocale(last)) {
					if(doppiaVocale) {
						doppiaVocale = false;
					} else {
						flushAndClear(stack);
					}
				}
				
				else if(isVocale(c) && isVocale(last)) {
					if(!alwaysTreatAsDiphthong) {
						flushAndClear(stack);
					}
					doppiaVocale = true;
				}
				
				// due consonanti semplici
				if(isConsonante(c) && isConsonante(last)) {
					flushAndClear(stack);
				}
				
				// due conosonanti uguali
				else if(isConsonante(c) && c == last) {
					flushAndClear(stack);
				}
				
				// c seguita da q
				else if(c == 'q' && last == 'c') {
					flushAndClear(stack);
				}
				
			}
			
			stack.add(c);
		}
		flushAndClear(stack);
		
		return result;
	}

	private void flushAndClear(List<Character> stack) {
		result.add(join(stack));
		stack.clear();
	}

	private Character last(List<Character> stack) {
		return stack.isEmpty()? null : stack.get(stack.size() - 1);
	}

	private String join(List<Character> stack) {
		return stack.stream().map(g -> g.toString()).collect(Collectors.joining());
	}

	private boolean isConsonante(Character c) {
		if(c == null) {
			return false;
		}
		return !isVocale(c);
	}

	private boolean isVocale(Character c) {
		if(c == null) {
			return false;
		}
		return VOWELS.contains(c);
	}
	
}
