package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stark.unifi.datanalyze.model.Document;

public class FleschReadingEaseCalculator implements IndexCalculator {

	private static final BigDecimal FACTOR_A = BigDecimal.valueOf(206.835);
	private static final BigDecimal FACTOR_B = BigDecimal.valueOf(1.015);
	private static final BigDecimal FACTOR_C = BigDecimal.valueOf(84.6);

	@Override
	public BigDecimal execute(Document document) {
		BigDecimal asl = document.getWordCount().divide(document.getSentenceCount(), 5, RoundingMode.HALF_UP);
		BigDecimal asw = document.getSyllableCount().divide(document.getWordCount(), 5, RoundingMode.HALF_UP);

		return FACTOR_A
				.subtract(FACTOR_B.multiply(asl))
				.subtract(FACTOR_C.multiply(asw))
				.setScale(2, RoundingMode.HALF_UP);
	}

}
