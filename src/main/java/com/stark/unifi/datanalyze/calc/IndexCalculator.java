package com.stark.unifi.datanalyze.calc;

import java.math.BigDecimal;

import com.stark.unifi.datanalyze.model.Document;

public interface IndexCalculator {

	static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
	
	BigDecimal execute(Document document);
	
}
