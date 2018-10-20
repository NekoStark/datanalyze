package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class FleschKincaidIndexCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double a = (double) document.getWordCount() / document.getSentenceCount();
		double b = (double) document.getSyllableCount() / document.getWordCount();

		return 0.39 * a + 11.8 * b - 15.59;
	}

}
