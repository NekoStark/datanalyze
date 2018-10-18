package com.stark.unifi.datanalyze.reader;

import com.stark.unifi.datanalyze.exception.TextExtractionException;

public class TextReader {

	private static final String PDF_EXTENSION = "pdf";
	private static final String TXT_EXTENSION = "txt";

	private TextReader() {
	}

	public static String read(String filePath) {
		int extensionIndex = filePath.lastIndexOf('.');
		String extension = filePath.substring(extensionIndex + 1);

		if (PDF_EXTENSION.equals(extension)) {
			return PdfTextReader.read(filePath);
		}

		if (TXT_EXTENSION.equals(extension)) {
			return TextFileReader.read(filePath);
		}

		throw new TextExtractionException("Unsupported file");
	}

}
