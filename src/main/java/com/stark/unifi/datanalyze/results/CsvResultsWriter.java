package com.stark.unifi.datanalyze.results;

import static java.util.stream.Collectors.joining;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.stark.unifi.datanalyze.calc.AutomatedReadabilityIndexCalculator;
import com.stark.unifi.datanalyze.calc.ColemanLiauIndexCalculator;
import com.stark.unifi.datanalyze.calc.FleschKincaidIndexCalculator;
import com.stark.unifi.datanalyze.calc.FleschReadingEaseCalculator;
import com.stark.unifi.datanalyze.calc.FogIndexCalculator;
import com.stark.unifi.datanalyze.calc.LixIndexCalculator;
import com.stark.unifi.datanalyze.calc.SmogIndexCalculator;
import com.stark.unifi.datanalyze.exception.ResultsWriterException;
import com.stark.unifi.datanalyze.model.Document;
import com.stark.unifi.datanalyze.model.Sentence;
import com.stark.unifi.datanalyze.util.CommandLineOptions;

public class CsvResultsWriter {

	private static final Logger LOGGER = Logger.getLogger(CsvResultsWriter.class.getName());

	private File outputDir;

	public CsvResultsWriter(CommandLineOptions opts) {
		outputDir = new File(opts.getOutputDir());
	}

	public void write(Document d, String fileId) {
		if (!outputDir.exists()) {
			throw new ResultsWriterException("Output directory doesn't not exist");
		}

		if (!outputDir.isDirectory()) {
			throw new ResultsWriterException("Specified path is not  directory");
		}

		File extractedFile = getOutputFile("extracted.txt");
		existsOrCreate(extractedFile);
		
		write(
			extractedFile,
			Arrays.asList(
//				d.toString()
//				"\n\n\n",
				d.getSentences().stream().map(Sentence::toString).collect(joining("\n"))
			),
			StandardOpenOption.TRUNCATE_EXISTING
		);
		
		File resultsFile = getOutputFile("result.csv");
		
		if(!existsOrCreate(resultsFile)) {
			LOGGER.log(Level.INFO, "Create header for file {0}", resultsFile);
			
			// Write header only if not appending
			write(resultsFile,
				Arrays.asList(
					"id", "Words", "Sentences", "Syllables", "Characters"//, 
//					"Automated Readability Index", "Coleman Liau Index",
//					"Flesch Reading Ease", "Flesch Kincaid Index", 
//					"Fog Index", "Lix Index", "SMOG Index"
				),
				StandardOpenOption.APPEND
			);
		}
			
		// Write document stats & indexes
		write(resultsFile,
			Arrays.asList(
				fileId,
				String.valueOf( d.getWordCount() ),
				String.valueOf( d.getSentenceCount() ),
				String.valueOf( d.getSyllableCount() ),
				String.valueOf( d.getCharacterCount() )//,
//				String.valueOf( new AutomatedReadabilityIndexCalculator().execute(d) ),
//				String.valueOf( new ColemanLiauIndexCalculator().execute(d) ),
//				String.valueOf( new FleschReadingEaseCalculator().execute(d) ),
//				String.valueOf( new FleschKincaidIndexCalculator().execute(d) ),
//				String.valueOf( new FogIndexCalculator().execute(d) ),
//				String.valueOf( new LixIndexCalculator().execute(d) ),
//				String.valueOf( new SmogIndexCalculator().execute(d ))
			),
			StandardOpenOption.APPEND
		);
	}

	private File getOutputFile(String fileName) {
		return new File(outputDir, fileName);
	}
	
	private boolean existsOrCreate(File file) {
		try {
			return !file.createNewFile();
		} catch (IOException e) {
			throw new ResultsWriterException(e);
		}
	}

	private void write(File file, List<String> output, StandardOpenOption option) {
		byte[] toWrite = (output.stream().collect(Collectors.joining(",")) + "\n").getBytes();
		try {
			Files.write(file.toPath(), toWrite, option);

		} catch (IOException e) {
			throw new ResultsWriterException(e);
		}
	}

}
