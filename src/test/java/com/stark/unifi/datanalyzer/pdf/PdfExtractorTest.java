package com.stark.unifi.datanalyzer.pdf;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.stark.unifi.datanalyze.exception.PdfExtractionException;
import com.stark.unifi.datanalyze.pdf.PdfExtractor;

public class PdfExtractorTest {

	private PdfExtractor pdfExtractor;
	
	@Before
	public void setUp() {
		pdfExtractor = new PdfExtractor();
	}
	
	@Test
	public void testExtract() throws Exception {
		String filePath = PdfExtractorTest.class.getResource("/pdf/dummy.pdf").getFile();
		assertEquals("Dummy PDF file", pdfExtractor.extract(filePath).trim());
	}
	
	@Test(expected=PdfExtractionException.class)
	public void testExtractException() throws Exception {
		pdfExtractor.extract("/path/to/non-existent.pdf");
	}
	
}
