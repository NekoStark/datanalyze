package com.stark.unifi.datanalyze.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.stark.unifi.datanalyze.calc.AutomatedReadabilityIndexCalculator;
import com.stark.unifi.datanalyze.calc.ColemanLiauIndexCalculator;
import com.stark.unifi.datanalyze.calc.FleschKincaidIndexCalculator;
import com.stark.unifi.datanalyze.calc.FleschReadingEaseCalculator;
import com.stark.unifi.datanalyze.calc.FogIndexCalculator;
import com.stark.unifi.datanalyze.calc.LixIndexCalculator;
import com.stark.unifi.datanalyze.calc.SmogIndexCalculator;
import com.stark.unifi.datanalyze.exception.ResultsWriterException;
import com.stark.unifi.datanalyze.model.Document;

public class ResultsWriter {

	private static final Logger LOGGER = Logger.getLogger(ResultsWriter.class.getName());

	private File outputDir;

	public ResultsWriter(CommandLineOptions opts) {
		outputDir = new File(opts.getOutputDir());
	}

	public void write(Document d, boolean skipExtractedFile) {
		if (!outputDir.exists()) {
			throw new ResultsWriterException("Output directory doesn't not exist");
		}

		if (!outputDir.isDirectory()) {
			throw new ResultsWriterException("Specified path is not  directory");
		}

		// Write extracted text
		if(!skipExtractedFile) {
			write(getOutputFile("extracted"), Collections.singletonList(d.getOriginalText()));
		}
		
		// Write document stats & indexes
		write(getOutputFile("result"), Arrays.asList(
			"Words: " + d.getWordCount(),
			"Sentences: " + d.getSentenceCount(),
			"Syllables: " + d.getSyllableCount(),
			"Characters: " + d.getCharacterCount(),
			"",
			"Automated Readability Index: " + new AutomatedReadabilityIndexCalculator().execute(d),
			"Coleman Liau Index: " + new ColemanLiauIndexCalculator().execute(d),
			"Flesch Reading Ease: " + new FleschReadingEaseCalculator().execute(d),
			"Flesch Kincaid Index: " + new FleschKincaidIndexCalculator().execute(d),
			"Fog Index: " + new FogIndexCalculator().execute(d),
			"Lix Index: " + new LixIndexCalculator().execute(d),
			"SMOG Index: " + new SmogIndexCalculator().execute(d)
		));
	}

	private File getOutputFile(String fileName) {
		File result = new File(outputDir, fileName + ".txt");
		try {
			if (!result.createNewFile()) {
				LOGGER.log(Level.INFO, "file {0} already exists, overwriting", fileName);
			}
		} catch (IOException e) {
			throw new ResultsWriterException(e);
		}

		return result;
	}

	private void write(File file, List<String> output) {
		try {
			Files.write(file.toPath(), output, StandardOpenOption.TRUNCATE_EXISTING);

		} catch (IOException e) {
			throw new ResultsWriterException(e);
		}
	}

}
