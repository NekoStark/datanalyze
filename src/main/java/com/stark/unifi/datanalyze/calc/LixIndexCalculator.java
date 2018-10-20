package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class LixIndexCalculator implements IndexCalculator {

	@Override
	public BigDecimal execute(Document document) {
		BigDecimal a = document.getWordCount().divide(document.getSentenceCount(), 5, RoundingMode.HALF_UP);
		BigDecimal b = document.getLongWordCount().multiply(ONE_HUNDRED).divide(document.getWordCount());
		
		return a.add(b).setScale(2, RoundingMode.HALF_UP);
	}

}
