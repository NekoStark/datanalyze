package com.stark.unifi.datanalyze.results;

import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.util.CommandLineOptions;

public class ResultsWriter {

	private ResultsWriter() {
	}
	
	public static void write(Document d, String inputFilePath, CommandLineOptions opts) {
		String fileId = inputFilePath.substring(
							inputFilePath.lastIndexOf('/') + 1,
							inputFilePath.lastIndexOf('.')
						);
		
		if(opts.isCsvOutput()) {
			new CsvResultsWriter(opts).write(d, fileId);
			
		} else {
			new BaseResultsWriter(opts).write(d);
			
		}
	}
	
}
