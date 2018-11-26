package com.stark.unifi.datanalyze.reader;

import com.stark.unifi.datanalyze.exception.TextExtractionException;

public class TextReader {

	private static final String PDF_EXTENSION = "pdf";
	private static final String TXT_EXTENSION = "txt";
	private static final String HTML_EXTENSION = "htm";

	private TextReader() {
	}

	public static String read(String filePath) {
		if (isPdf(filePath)) {
			return PdfTextReader.read(filePath);
		}

		if (isTxt(filePath)) {
			return TextFileReader.read(filePath);
		}
		
		if (isHtml(filePath)) {
			return HtmlTextReader.read(filePath);
		}

		throw new TextExtractionException("Unsupported file");
	}

	public static boolean isPdf(String filePath) {
		return PDF_EXTENSION.equals(getExtension(filePath));
	}

	public static boolean isTxt(String filePath) {
		return TXT_EXTENSION.equals(getExtension(filePath));
	}
	
	public static boolean isHtml(String filePath) {
		return HTML_EXTENSION.equals(getExtension(filePath));
	}

	public static String getExtension(String filePath) {
		int extensionIndex = filePath.lastIndexOf('.');
		return filePath.substring(extensionIndex + 1);
	}
}
