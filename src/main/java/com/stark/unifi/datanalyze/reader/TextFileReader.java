package com.stark.unifi.datanalyze.reader;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.stark.unifi.datanalyze.exception.TextExtractionException;

public class TextFileReader {

	private TextFileReader() {
	}
	
	static String read(String filePath) {
		try {
			return Files.readAllLines(Paths.get(filePath), Charset.forName("ISO-8859-1")).stream().collect(joining());
			
		} catch (IOException e) {
			throw new TextExtractionException(e);
			
		}
	}
	
}
