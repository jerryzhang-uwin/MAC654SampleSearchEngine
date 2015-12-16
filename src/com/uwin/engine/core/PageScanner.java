package com.uwin.engine.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.uwin.engine.advanced.TST;

/**
 * Responsible for read a file into a HashMap with word-frequency entries
 * @author 
 */
public class PageScanner {
	
	//private static final String CHARSET_NAME = "ISO-8859-1";
    private static final Locale LOCALE = Locale.US;
    
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
	
	private static Scanner scanner;
    
	private static void openFile(File file) {
        try {
			//scanner = new Scanner(file, CHARSET_NAME);
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			Logging.err("Could not open file: " + file);
		}
        scanner.useLocale(LOCALE);
    }
    
    private static void closeFile() {
    	scanner.close();
    }
    
    private static boolean isEmpty() {
        return !scanner.hasNext();
    }
    
    private static String readStringByPattern(Pattern pattern) {
        return scanner.useDelimiter(pattern).next();
    }
    
    
    /**
     * Scan all words of the pages into a TST.
     * @param fileName
     * @return
     */
    public static TST<Integer> scan(String fileName) {
    	
    	Logging.info("Scanning file: " + fileName);
    	
    	TST<Integer> pageHashMap = new TST<Integer>();
    	String stringKey = "";
    	
    	File file = new File(fileName);
    	openFile(file);
    	
    	while (!isEmpty()) {
    		// Read next String. And case insensitive - all keywords are converted to lower case.
    		stringKey = readStringByPattern(WHITESPACE_PATTERN).toLowerCase();
    		
    		if (pageHashMap.contains(stringKey)) {
    			// When a key is found, increase its value by 1
    			pageHashMap.put(stringKey, pageHashMap.get(stringKey) + 1);
    		} else {
    			// When a key cannot be found, set its value to 1
    			pageHashMap.put(stringKey, 1);
    		}
    	}
    	
    	closeFile();
    	Logging.info("File scanning completed");
    	
    	return pageHashMap;
    }
    
    
    /**
     * Only for debugging.
     * @param map
     */
    public static void iterate(HashMap<String, Integer> map) {
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			Logging.info(entry.getKey() + " ---> " + entry.getValue());
		}
    }
    
}
