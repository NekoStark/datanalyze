package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class FogIndexCalculator implements IndexCalculator {

	private static final BigDecimal FACTOR = BigDecimal.valueOf(0.4);

	@Override
	public BigDecimal execute(Document document) {
		BigDecimal a = document.getWordCount().divide(document.getSentenceCount(), 5, RoundingMode.HALF_UP);
		BigDecimal b = document.getComplexWordCount(3).divide(document.getWordCount(), 5, RoundingMode.HALF_UP);

		return FACTOR.multiply(a.add(ONE_HUNDRED.multiply(b))).setScale(2, RoundingMode.HALF_UP);
	}

}
