package com.stark.unifi.datanalyze.calc;

import com.stark.unifi.datanalyze.model.Document;

public class FleschReadingEaseCalculator extends IndexCalculator {

	@Override
	public double indexCalcImpl(Document document) {
		double asl = (double) document.getWordCount() / document.getSentenceCount();
		double asw = (double) document.getSyllableCount() / document.getWordCount();

		return 206.835 - 1.015 * asl - 84.6 * asw;
	}

}
