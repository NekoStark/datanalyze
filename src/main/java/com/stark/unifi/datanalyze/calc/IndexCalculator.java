package com.stark.unifi.datanalyze.calc;

import org.apache.commons.math3.util.Precision;

import com.stark.unifi.datanalyze.model.Document;

public abstract class IndexCalculator {

	public double execute(Document document) {
		return Precision.round(indexCalcImpl(document), 2);
	}

	abstract double indexCalcImpl(Document document);

}
