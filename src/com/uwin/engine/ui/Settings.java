package com.uwin.engine.ui;

/**
 * Stores constant string settings such as folder paths.
 */
public class Settings {
	
	// Change this folder to your own source file location.
	public static final String SEARCH_ENGINE_HOME = "D:\\temp\\searchengine";
	
	public static final String SOURCE_HTML_FOLDER = SEARCH_ENGINE_HOME + "\\html\\";
	public static final String SOURCE_TXT_FOLDER = SEARCH_ENGINE_HOME + "\\txt\\";
	public static final String URL_HTML_FOLDER = "file:///" + SOURCE_HTML_FOLDER;
}
