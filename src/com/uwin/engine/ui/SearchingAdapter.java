package com.uwin.engine.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.uwin.engine.advanced.TST;
import com.uwin.engine.core.KeyValuePair;
import com.uwin.engine.core.RankingMgr;


public class SearchingAdapter {
	
	public static String getSearchIndicationPage() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html>");
		builder.append("<body>");
		builder.append("<p><h2>Enter keyword(s) and click Search button.</h2></p>");
		builder.append("<p>Dataset home directory: " + Settings.SEARCH_ENGINE_HOME + "</p>");
		builder.append("</body>");
		builder.append("</html>");
		return builder.toString();
	}
	
	/**
	 * Return search results list as HTML format.
	 */
	public static String getResultsPage(HashMap<String, TST<Integer>> scannedFiles, 
			String keywordsLine) {
		
		RankingMgr ranker = new RankingMgr(scannedFiles);
//		List<KeyValuePair<Integer, String>> resultList = ranker.rankPagesBySingleKeyword(keyword);
		
		List<String> keywords = filterKeywords(keywordsLine);
		List<KeyValuePair<Integer, String>> resultList = ranker.rankPagesByMultipleKeywords(keywords);
		
		// Steps to build a very simple HTML search results list.
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html>");
		builder.append("<head><title>Search Results</title></head>");
		builder.append("<body>");
		
		String separatedKeywords = "";
		for (String s : keywords) {
			separatedKeywords += "\"" + s + "\" "; 
		}
		
		builder.append("<p><h2>Search results for keyword(s): " + separatedKeywords + "</h2></p>");
		
		for (KeyValuePair<Integer, String> line : resultList) {
			String freq = String.valueOf(line.getKey());
			String title = String.valueOf(line.getValue().replace(".txt", ""));
			
			builder.append("<p>" 
					+ "<span style=\"color:black;background-color:#ffff66;padding:0 5px;font-weight:bold\">"
					+ freq 
					+ "</span>"
					+ "<span style=\"padding:0 10px;\"></span>" 
					+ "<a href=\"" 
					+ Settings.URL_HTML_FOLDER
					+ title + "\">" + title + "</a></p>");
		}
		
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}
	
	
	private static List<String> filterKeywords(String keywordsLine) {
		// Define the regex to filter unnecessary characters.
		String splitTokenRegex = "[^a-zA-Z0-9_-]+";
		// Create the list of keywords.
		return new ArrayList<String>(Arrays.asList(keywordsLine.split(splitTokenRegex)));
	}

}
