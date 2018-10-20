package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class ColemanLiauIndexCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double l = (double) document.getCharacterCount() / document.getWordCount() * 100;
		double s = (double) document.getSentenceCount() / document.getWordCount() * 100;

		return 0.0588 * l - 0.296 * s - 15.8;
	}

}
