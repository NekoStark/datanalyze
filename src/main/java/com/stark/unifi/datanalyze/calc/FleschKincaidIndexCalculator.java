package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class FleschKincaidIndexCalculator implements IndexCalculator {

	private static final BigDecimal FACTOR_A = BigDecimal.valueOf(0.39);
	private static final BigDecimal FACTOR_B = BigDecimal.valueOf(11.8);
	private static final BigDecimal FACTOR_C = BigDecimal.valueOf(15.59);

	@Override
	public BigDecimal execute(Document document) {
		BigDecimal a = document.getWordCount().divide(document.getSentenceCount(), 5, RoundingMode.HALF_UP);
		BigDecimal b = document.getSyllableCount().divide(document.getWordCount(), 5, RoundingMode.HALF_UP);
		return FACTOR_A.multiply(a).add(FACTOR_B.multiply(b)).subtract(FACTOR_C).setScale(2, RoundingMode.HALF_UP);
	}

}
