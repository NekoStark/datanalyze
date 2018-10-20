package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class SmogIndexCalculator implements IndexCalculator {

	private static final BigDecimal FACTOR_A = new BigDecimal(1.043);
	private static final BigDecimal FACTOR_B = new BigDecimal(30);
	private static final BigDecimal FACTOR_C = new BigDecimal(3.1291);
	
	@Override
	public BigDecimal execute(Document document) {
		BigDecimal a = document.getComplexWordCount(2).multiply(
							FACTOR_B.divide(document.getPhraseCount(), 5, RoundingMode.HALF_UP)
						);
		
		BigDecimal b = BigDecimal.valueOf(Math.sqrt(a.doubleValue()));
		
		return FACTOR_A.multiply(b).add(FACTOR_C).setScale(2, RoundingMode.HALF_UP);
	}

}
