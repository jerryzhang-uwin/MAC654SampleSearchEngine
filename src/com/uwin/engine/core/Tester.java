package com.uwin.engine.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 * Only used for testing existing classes. Contains a main() method.
 */
public class Tester {

	private static final String SAMPLE_PAGE_FILE = "D:\\temp\\samplepage.txt";
	private static final String SAMPLE_FOLDER = "D:\\temp\\searchengine\\txt\\";
	
//	public static void main(String[] args) {
//		
//		HashMap<String, HashMap<String, Integer>> scannedFiles 
//				= FolderScanner.scanToHashMap(SAMPLE_FOLDER);
//		
//		//FolderScanner.iterate(scannedFiles);
//		
//		RankingMgr rank = new RankingMgr(scannedFiles);
//		
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//        	System.out.println("Enter single keyword:");
//	        try{
//	            String s = br.readLine();
//	            List<KeyValuePair<Integer, String>> resultList = rank.rankPagesBySingleKeyword(s);
//	            for (KeyValuePair<Integer, String> pair : resultList) {
//	            	System.out.println(pair.toString());
//	            	System.out.println();
//	            }
//	        }catch(IOException e){
//	            System.err.println("Invalid IO");
//	        }
//        }
//		
//
//	}

}
