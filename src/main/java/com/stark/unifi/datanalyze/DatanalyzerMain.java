package com.stark.unifi.datanalyze;

import com.stark.unifi.datanalyze.analyzer.TextAnalyzer;
import com.stark.unifi.datanalyze.exception.CommandLineOptionsParseException;
import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.pdf.PdfExtractor;
import com.stark.unifi.datanalyze.util.CommandLineOptions;

public class DatanalyzerMain {

	public static void main(String[] args) {
		
		CommandLineOptions opts = new CommandLineOptions();
		try {
			opts.parse(args);
			
			// Extract text from pdf
			PdfExtractor pdfExtractor = new PdfExtractor();
			String text = pdfExtractor.extract(opts.getInputFile());
			
			// Analyze extracted text
			TextAnalyzer analyzer = new TextAnalyzer();
			Document doc = analyzer.analyzeText(text);
			
			//...
			
		} catch(CommandLineOptionsParseException e) {
			e.printStackTrace();
			opts.printHelp();
			
		}
		
		
	}
	
}
