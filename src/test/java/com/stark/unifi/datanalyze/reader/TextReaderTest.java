package com.stark.unifi.datanalyze.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

import org.junit.Test;

import com.stark.unifi.datanalyze.exception.TextExtractionException;

public class TextReaderTest {

	@Test
	public void testReadUnsupportedFile() {
		try {
			TextReader.read("unsupportedFile.ods");
			fail();
			
		} catch(TextExtractionException e) {
			assertEquals("Unsupported file", e.getMessage());
		}
	}

	@Test
	public void testReadTxt() throws Exception {
		String filePath = TextReaderTest.class.getResource("/txt/dummy.txt").getFile();
		assertEquals("Dummy TXT file", TextReader.read(filePath).trim());
	}
	
	@Test
	public void testReadTxtNotExistent() throws Exception {
		try {
			TextReader.read("/path/to/non-existent.txt");
			fail();
			
		} catch(TextExtractionException e) {
			assertTrue(e.getCause() instanceof NoSuchFileException);
		}
	}
	
	@Test
	public void testReadPdf() throws Exception {
		String filePath = TextReaderTest.class.getResource("/pdf/dummy.pdf").getFile();
		assertEquals("Dummy PDF file", TextReader.read(filePath).trim());
	}
	
	@Test
	public void testReadPdfNotExistent() throws Exception {
		try {
			TextReader.read("/path/to/non-existent.pdf");
			fail();
			
		} catch(TextExtractionException e) {
			assertTrue(e.getCause() instanceof FileNotFoundException);
		}
	}
	
}
