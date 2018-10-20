package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class AutomatedReadabilityIndexCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double a = (double) document.getCharacterCount() / document.getWordCount();
		double b = (double) document.getWordCount() / document.getSentenceCount();
		
		return (4.71 * a) + (0.5 * b)- 21.43;
	}	
}
