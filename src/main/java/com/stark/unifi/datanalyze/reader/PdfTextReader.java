package com.stark.unifi.datanalyze.reader;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.stark.unifi.datanalyze.exception.TextExtractionException;

public class PdfTextReader {

	private PdfTextReader() {
	}
	
	static String read(String filePath) {
		File file = new File(filePath);
		
		try (PDDocument document = PDDocument.load(file)) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			return pdfStripper.getText(document);

		} catch (IOException e) {
			throw new TextExtractionException(e);
		}
	}

}
