package com.stark.unifi.datanalyze.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlTextReader {

	private HtmlTextReader() {
	}
	
	static String read(String filePath) {
		String text = TextFileReader.read(filePath);
		Document parsed = Jsoup.parse(text);
		parsed.select("table").remove();
//		result = result.replaceAll("\\<.*?>"," ");
//		result = result.replaceAll("nbsp", " ");
		return parsed.text();
	}
	
}
