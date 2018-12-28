package com.stark.unifi.datanalyze.reader;

import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlTextReader {

	private HtmlTextReader() {
	}
	
	static String read(String filePath) {
		String text = TextFileReader.read(filePath);
		Document parsed = Jsoup.parse(text);
//		Elements sentences = parsed.getElementsByTag("p");
//		String result = sentences.stream().map(Element::text).collect(Collectors.joining("\n"));
//		
//		if(result.trim().isEmpty()) {
//			sentences = parsed.getElementsByTag("div");
//			result = 
//		}
		
		parsed.getElementsByTag("table").remove();
		
		return parsed.text();
	}
	
}
