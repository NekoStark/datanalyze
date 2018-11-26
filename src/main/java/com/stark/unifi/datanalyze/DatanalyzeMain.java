package com.stark.unifi.datanalyze;

import com.stark.unifi.datanalyze.analyzer.TextAnalyzer;
import com.stark.unifi.datanalyze.exception.CommandLineOptionsParseException;
import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.reader.TextReader;
import com.stark.unifi.datanalyze.results.ResultsWriter;
import com.stark.unifi.datanalyze.util.CommandLineOptions;

public class DatanalyzeMain {

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$s] [%2$s] %5$s%6$s%n");
	}
	
	public static void main(String[] args) {
		CommandLineOptions opts = new CommandLineOptions();
		try {
			opts.parse(args);
			String inputFilePath = opts.getInputFile();

			String text = TextReader.read(inputFilePath);
			TextAnalyzer analyzer = new TextAnalyzer();
			Document doc = analyzer.analyzeText(text);
			
			ResultsWriter.write(doc, inputFilePath, opts);
			
		} catch(CommandLineOptionsParseException e) {
			opts.printHelp();
			
		}
		
		
	}
	
}
