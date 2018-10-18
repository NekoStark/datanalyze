package com.stark.unifi.datanalyze.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.stark.unifi.datanalyze.exception.CommandLineOptionsParseException;

public class CommandLineOptions {

	private Options options;

	private String inputFile;
	private String outputDir;
	private boolean alwaysTreatAsDiphthong;

	public CommandLineOptions() {
		options = new Options();
		options.addOption("i", "inputFile", true, "the pdf or txt file to analyze");
		options.addOption("o", "outputDir", true, "the directory where to write output files");
		options.addOption("d", "treatAsDiphthong", true,
				"if true never breaks groups of vowels. Otherwhise always break group of vowels (hyatus)˙");
	}

	public void parse(String[] args) {
		CommandLine cmd = getCommandLine(args);

		if (!cmd.hasOption("i") || !cmd.hasOption("o")) {
			throw new CommandLineOptionsParseException("Missing options");
		}

		this.inputFile = cmd.getOptionValue("i");
		this.outputDir = cmd.getOptionValue("o");
		this.alwaysTreatAsDiphthong = cmd.hasOption("o") ? Boolean.valueOf(cmd.getOptionValue("o")) : true;
	}

	public String getInputFile() {
		return inputFile;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public boolean isAlwaysTreatAsDiphthong() {
		return alwaysTreatAsDiphthong;
	}

	private CommandLine getCommandLine(String[] args) {
		CommandLineParser parser = new DefaultParser();
		try {
			return parser.parse(options, args);

		} catch (ParseException e) {
			throw new CommandLineOptionsParseException(e);
		}
	}

	public void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("java -jar datanalyze", options);
	}

}
