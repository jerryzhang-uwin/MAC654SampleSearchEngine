package com.uwin.engine.core;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Responsible for scanning of all files under a folder and store fileName-frequencyHashMap entries
 * @author 
 *
 */
public class FolderScanner {
	
	/**
	 * 
	 * @param folderName
	 * @return
	 */
	public static HashMap<String, HashMap<String, Integer>> scanToHashMap(String folderName) {
		
		Logging.info("Scanning all files in folder: " + folderName);
		
		HashMap<String, HashMap<String, Integer>> scannedFiles 
				= new HashMap<String, HashMap<String, Integer>>();
		
		// Scan each page of the folder to Hash Maps.
		File folder = new File(folderName);
		File[] filesInTheFolder = folder.listFiles();
		
		for (File file : filesInTheFolder) {
			if (file.isFile()) {
		    	HashMap<String, Integer> fileHashMap = PageScanner.scanToHashMap(file.getAbsolutePath());
//		    	scannedFiles.put(file.getAbsolutePath(), fileHashMap);
		    	scannedFiles.put(file.getName(), fileHashMap);
			} else {
				Logging.err(file.getAbsolutePath() + " is not a file");
			}
		}
		
		Logging.info("Folder scanning completed");
		
		return scannedFiles;
	}
	
    /**
     * Only for debugging purpose.
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
