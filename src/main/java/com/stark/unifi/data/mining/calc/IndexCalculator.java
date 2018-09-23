package com.stark.unifi.data.mining.calc;

import java.math.BigDecimal;

import com.stark.unifi.data.mining.model.Document;

public interface IndexCalculator {

	static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
	
	BigDecimal execute(Document document);
	
}
