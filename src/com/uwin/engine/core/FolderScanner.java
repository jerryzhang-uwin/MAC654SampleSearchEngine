package com.uwin.engine.core;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.uwin.engine.advanced.TST;

/**
 * Responsible for scanning of all files under a folder and store fileName-frequencyHashMap entries
 * @author 
 *
 */
public class FolderScanner {
	
	/**
	 * Scan all pages of a folder into a HashMap, with the key as file name and value the 
	 * TST structure of that page.
	 * @param folderName
	 * @return
	 */
	public static HashMap<String, TST<Integer>> scan(String folderName) {
		
		Logging.info("Scanning all files in folder: " + folderName);
		
		HashMap<String, TST<Integer>> scannedFiles 
				= new HashMap<String, TST<Integer>>();
		
		// Scan each page of the folder to Hash Maps.
		File folder = new File(folderName);
		File[] filesInTheFolder = folder.listFiles();
		
		for (File file : filesInTheFolder) {
			if (file.isFile()) {
				TST<Integer> fileHashMap = PageScanner.scan(file.getAbsolutePath());
		    	scannedFiles.put(file.getName(), fileHashMap);
			} else {
				Logging.err(file.getAbsolutePath() + " is not a file");
			}
		}
		
		Logging.info("Folder scanning completed");
		
		return scannedFiles;
	}
	
    /**
     * Only for debugging.
     * @param map
     */
    public static void iterate(HashMap<String, HashMap<String, Integer>> map) {
		Iterator<Entry<String, HashMap<String, Integer>>> it = map.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, HashMap<String, Integer>> entry = it.next();
			Logging.info(">>>>>>>>>>>> " + entry.getKey());
			PageScanner.iterate(entry.getValue());
		}
    }

}
