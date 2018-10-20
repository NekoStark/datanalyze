package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class FogIndexCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double a = (double) document.getWordCount() / document.getSentenceCount();
		double b = (double) document.getComplexWordCount(3) / document.getWordCount();

		return 0.4 * (a + (100 * b));
	}

}
