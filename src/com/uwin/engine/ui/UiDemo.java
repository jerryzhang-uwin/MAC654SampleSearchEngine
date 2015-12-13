package com.uwin.engine.ui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class UiDemo extends Application {
	
	private Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("654 Search Engine");
		scene = new Scene(new SearchEngineUI(), 900, 700, Color.web("#666970"));
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * The primary UI element - including a keyword input box, a search button and an
	 * embedded browser.
	 */
	private class SearchEngineUI extends Region {
		
		private HBox searchBar = new HBox();
		private TextField keywordInputBox = new TextField();
		private Button searchButton = new Button("Search");
		
		private WebView browser = new WebView();
		private WebEngine webEngine = browser.getEngine();
		
		public SearchEngineUI() {
			// Add all nodes to UI. 
	        searchBar.getChildren().addAll(keywordInputBox, searchButton);
			getChildren().add(searchBar);
			getChildren().add(browser);
			
			webEngine.loadContent(SearchingAdapter.getSearchIndicationPage());
			
			// Search button action.
			searchButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	
			    	String keyword = keywordInputBox.getText();
			    	if (keyword.trim().equals("")) {
			    		webEngine.loadContent(SearchingAdapter.getSearchIndicationPage());
			    		return;
			    	}
			    	
			    	// Call another background thread to do the searching, which would be time demanding.
			    	SearchingService search = new SearchingService(keyword);
			    	
			        search.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			            @Override
			            public void handle(WorkerStateEvent t) {
			                //System.out.println("done:" + t.getSource().getValue());
			                webEngine.loadContent(t.getSource().getValue().toString());
			            }
			        });
			        // Start the background service.
			        search.start();
			    }
			});
			
			
	        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
				@Override
				public void changed(ObservableValue<? extends State> observableValue, State oldState, State newState) {
					if (newState == State.SUCCEEDED) {
						
			            EventListener listener = new EventListener() {
							@Override
							public void handleEvent(Event ev) {
								String href = ((Element)ev.getTarget()).getAttribute("href");
								keywordInputBox.setText(href);
							}
			            };
			            
			            
			            Document doc = webEngine.getDocument();
//			            Element el = doc.get .getElementById("textarea");
//			            ((EventTarget) el).addEventListener("keypress", listener, false);
			            
					}
				}
	        });
			
		}
		
		/**
		 * Set the layout of UI.
		 */
	    @Override 
	    protected void layoutChildren() {
	        double w = getWidth();
	        double h = getHeight();
	        
	        double searchBarHeight = 90;
	        
	        searchBar.setPadding(new Insets(30, 15, 15, w/2 - 300));
	        searchBar.setStyle("-fx-background-color: #336699;");
	        searchBar.setSpacing(20);
	        
	        keywordInputBox.setPrefSize(500, 30);
	        searchButton.setPrefSize(80, 30);
	        
	        layoutInArea(searchBar, 0, 0, w, searchBarHeight, 0, HPos.CENTER, VPos.CENTER);
	        layoutInArea(browser, 0, searchBarHeight + 5, w, h - searchBarHeight, 0, HPos.CENTER, VPos.CENTER);
	    }
	}

}
