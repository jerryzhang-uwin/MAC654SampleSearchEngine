package com.uwin.engine.ui;

import java.util.HashMap;

import com.uwin.engine.advanced.TST;
import com.uwin.engine.core.FolderScanner;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * The background service to do searching job when clicking "Search" button.
 */
public class SearchingService extends Service<String> {
	
	private static 	HashMap<String, TST<Integer>> scannedFiles; 
	private String keyword;
	
	public SearchingService(String keyword) {
		this.keyword = keyword;
//		if (scannedFiles == null) {
//			scannedFiles = FolderScanner.scan(Settings.SOURCE_TXT_FOLDER);
//		}
	}
	
	/**
	 * Calls the adapter to do searching and get the result HTML string.
	 */
	@Override
	protected Task<String> createTask() {
		
        return new Task<String>() {
            @Override
            protected String call() {
        		if (scannedFiles == null) {
        			scannedFiles = FolderScanner.scan(Settings.SOURCE_TXT_FOLDER);
        			
        			// Return null since this step is only used for search engine 
        			// initialization (scan files).
        			return null;
        		}
        		
            	return SearchingAdapter.getResultsPage(scannedFiles, keyword);
            }
        };
	}

}
