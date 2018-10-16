package com.stark.unifi.datanalyze.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.stark.unifi.datanalyze.exception.PdfExtractionException;

public class PdfExtractor {

	public String extract(String filePath) {
		File file = new File(filePath);
		
		try (PDDocument document = PDDocument.load(file)) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(document);

		} catch (IOException e) {
			throw new PdfExtractionException(e);
		}
	}

}
