package com.uwin.engine.ui;

import java.util.HashMap;
import java.util.List;

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
	public static String getResultsPage(HashMap<String, HashMap<String, Integer>> scannedFiles, 
			String keyword) {
		
		RankingMgr ranker = new RankingMgr(scannedFiles);
		List<KeyValuePair<Integer, String>> resultList = ranker.rankPagesBySingleKeyword(keyword);
		
		// Steps to build a very simple HTML search results list.
		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html>");
		builder.append("<head><title>Search Results</title></head>");
		builder.append("<body>");
		builder.append("<p><h2>Search results for keyword(s): \"" + keyword + "\"</h2></p>");
		
		for (KeyValuePair<Integer, String> line : resultList) {
			String freq = String.valueOf(line.getKey());
			String title = String.valueOf(line.getValue().replace(".txt", ".htm"));
			
			builder.append("<p>" + freq + " " + "<a href=\"" 
					+ Settings.URL_HTML_FOLDER
					+ title + "\">" + title + "</a></p>");
		}
		
		builder.append("</body>");
		builder.append("</html>");
		
		return builder.toString();
	}

}
