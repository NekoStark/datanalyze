package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class AutomatedReadabilityIndexCalculator implements IndexCalculator {

	public static final BigDecimal FACTOR_A = BigDecimal.valueOf(4.71);
	public static final BigDecimal FACTOR_B = BigDecimal.valueOf(0.5);
	public static final BigDecimal FACTOR_C = BigDecimal.valueOf(21.43);
	
	@Override
	public BigDecimal execute(Document document) {
		BigDecimal a = document.getCharacterCount().divide(document.getWordCount(), 2, RoundingMode.HALF_UP);
		BigDecimal b = document.getWordCount().divide(document.getPhraseCount(), 2, RoundingMode.HALF_UP);
		
		return FACTOR_A.multiply(a)
						.add( FACTOR_B.multiply(b) )
						.subtract(FACTOR_C)
						.setScale(2, RoundingMode.HALF_UP);
	}	
}
