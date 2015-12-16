package com.uwin.engine.convert;

import java.util.ArrayList;

import com.uwin.engine.core.KeyValuePair;
import com.uwin.engine.ui.Settings;

public class ConvertHtmlFiles {
	
	public static void main(String[] args) {

		// Read all html contents and file names to a list.
		ArrayList<KeyValuePair<String, String>> allContents= FileReader.readFolder(Settings.SOURCE_HTML_FOLDER);

		// Generate txt output files.
		for (KeyValuePair<String, String> entry : allContents) {
			String txtFileName = Settings.SOURCE_TXT_FOLDER + entry.getKey() + ".txt";
			JsoupParser.parse(entry.getValue(), txtFileName);
		}
	}

}
