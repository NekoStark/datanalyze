package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class SmogIndexCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double a = (double) document.getComplexWordCount(2) * 30 / document.getSentenceCount();

		return 1.043 * Math.sqrt(a) + 3.1291;
	}

}
