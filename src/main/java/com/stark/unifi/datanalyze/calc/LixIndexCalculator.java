package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class LixIndexCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double a = (double) document.getWordCount() / document.getSentenceCount();
		double b = (double) (document.getLongWordCount() * 100) / document.getWordCount();

		return a + b;
	}

}
