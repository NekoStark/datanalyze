package com.stark.unifi.datanalyze.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.stark.unifi.datanalyzer.analyzer.TextAnalyzerTest;

public class FileContentReader {

	// TODO gestire eccezione in modo corretto
	public String getText(String resourcePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		String fileName = TextAnalyzerTest.class.getResource(resourcePath).getPath();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(sb::append);
		}
		
		return sb.toString();
	}
	
}
