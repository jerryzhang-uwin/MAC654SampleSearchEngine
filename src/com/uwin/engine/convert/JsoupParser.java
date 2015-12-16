package com.uwin.engine.convert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupParser {
	
	/**
	 * Parse all body text of html content to a file.
	 * @param htmlContent
	 * @param outputFile
	 */
	public static void parse(String htmlContent, String outputFile) {
		Document doc = Jsoup.parse(htmlContent);
		String bodyText = doc.body().text();
		
		// Write to file.
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(outputFile, false));
			bw.write(bodyText);
	        bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
