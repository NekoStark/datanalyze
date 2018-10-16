package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class ColemanLiauIndexCalculator implements IndexCalculator {

	public static final BigDecimal FACTOR_A = BigDecimal.valueOf(0.0588);
	public static final BigDecimal FACTOR_B = BigDecimal.valueOf(0.296);
	public static final BigDecimal FACTOR_C = BigDecimal.valueOf(15.8);
	
	@Override
	public BigDecimal execute(Document document) {
		BigDecimal l = document.getCharacterCount().divide(document.getWordCount(), 2, RoundingMode.HALF_UP).multiply(ONE_HUNDRED);
		BigDecimal s = document.getPhraseCount().divide(document.getWordCount(), 2, RoundingMode.HALF_UP).multiply(ONE_HUNDRED);
		
		return FACTOR_A.multiply(l)
					.subtract(FACTOR_B.multiply(s))
					.subtract(FACTOR_C)
					.setScale(2, RoundingMode.HALF_UP);
	}

}
