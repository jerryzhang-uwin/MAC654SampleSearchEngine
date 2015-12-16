package com.uwin.engine.convert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.uwin.engine.core.KeyValuePair;

public class FileReader {
	
	/**
	 * To read single file to String
	 * @param htmlFileName
	 * @return
	 */
	public static String readFile(String htmlFileName) {
		
		String result = "";
		
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(htmlFileName));
			result = new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Fail to read file: " + htmlFileName);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * To read all files in a folder.
	 * @param folderName
	 * @return
	 */
	public static ArrayList<KeyValuePair<String, String>> readFolder(String folderName) {
		
		ArrayList<KeyValuePair<String, String>> results = new ArrayList<KeyValuePair<String, String>>();
		
		File folder = new File(folderName);
		File[] filesInTheFolder = folder.listFiles();
		
		for (File file : filesInTheFolder) {
			if (file.isFile()) {
				String name = file.getName();
				String path = file.getAbsolutePath();
				
				KeyValuePair<String, String> entry 
					= new KeyValuePair<String, String>(name, readFile(path));
				
				results.add(entry);
			}
		}
		
		return results;
	}

}
