package com.stark.unifi.datanalyze.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.stark.unifi.datanalyze.analyzer.TextAnalyzerTest;
import com.stark.unifi.datanalyze.exception.FileContentReaderException;

public class FileContentReader {

	public String read(String resourcePath) {
		StringBuilder sb = new StringBuilder();
		String fileName = TextAnalyzerTest.class.getResource(resourcePath).getPath();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(sb::append);
			
		} catch(IOException e) {
			throw new FileContentReaderException(e);
		}
		
		return sb.toString();
	}
	
}
