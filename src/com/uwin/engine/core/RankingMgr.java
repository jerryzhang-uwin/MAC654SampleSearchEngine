package com.uwin.engine.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.uwin.engine.advanced.TST;

/**
 * Responsible for ranking pages by a specific keyword.
 * @author
 *
 */
public class RankingMgr {
	private HashMap<String, TST<Integer>> sourcePages;
		
	public RankingMgr(HashMap<String, TST<Integer>> pages) {
		this.sourcePages = pages;
	}
	
	/**
	 * Ranking pages by the occurrence of a single keyword
	 * @param keyword
	 * @return
	 */
	public List<KeyValuePair<Integer, String>> rankPagesBySingleKeyword(String keyword) {
		
		// Create an ArrayList to save sorted keyword frequencies and associated page file names.
		// Assign the ArrayList to a List interface.
		List<KeyValuePair<Integer, String>> frequencyFileNamePair 
				= new ArrayList<KeyValuePair<Integer, String>>();
		
		String fileName;
		Integer keywordFrequency;
		
		// Iterate over all scanned source pages to populate the List.
		Iterator<Entry<String, TST<Integer>>> it = this.sourcePages.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, TST<Integer>> pageEntry = it.next();
			fileName = pageEntry.getKey();
			
			keywordFrequency = pageEntry.getValue().get(keyword);
			// Determine if the keyword exists in the page or not.
			if (keywordFrequency == null) {
				keywordFrequency = 0;
			}
			
			// Store the frequency-keyword association to the List
			frequencyFileNamePair.add(new KeyValuePair<Integer, String>(keywordFrequency, fileName));
		}
		
		// Sort the List by high-low frequency with Collections.sort(), which is an 
		// implementation of Merge Sort.
		Collections.sort(frequencyFileNamePair, Collections.reverseOrder());
		
		return frequencyFileNamePair;
	}
	
	/**
	 * Ranking pages by the sum occurrences of multiple keywords
	 * @param keyword
	 * @return
	 */
	public List<KeyValuePair<Integer, String>> rankPagesByMultipleKeywords(List<String> keywords) {
		
		// Create an ArrayList to save sorted keywords frequencies and associated page file names.
		// Assign the ArrayList to a List interface.
		List<KeyValuePair<Integer, String>> frequencyFileNamePair 
				= new ArrayList<KeyValuePair<Integer, String>>();
		
		String fileName;
		Integer keywordFrequency;
		Integer tempFrequency;
		
		// Iterate over all scanned source pages to populate the List.
		Iterator<Entry<String, TST<Integer>>> it = this.sourcePages.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, TST<Integer>> pageEntry = it.next();
			fileName = pageEntry.getKey();
			
			keywordFrequency = 0;
			
			// Iterate over all keywords.
			for (String keyword : keywords) {
				
				if (keyword.equals("")) {
					continue;
				}
				
				tempFrequency = pageEntry.getValue().get(keyword);
				if (tempFrequency == null) {
					tempFrequency = 0;
				}
				keywordFrequency += tempFrequency;
			}
			
			// Store the frequency-keyword association to the List if the occurrence number 
			// is greater than 0.
			if (keywordFrequency > 0) {
				frequencyFileNamePair.add(new KeyValuePair<Integer, String>(keywordFrequency, fileName));
			}
		}
		
		// Sort the List by high-low frequency with Collections.sort(), which is an 
		// implementation of Merge Sort.
		Collections.sort(frequencyFileNamePair, Collections.reverseOrder());
		
		return frequencyFileNamePair;
	}

}
