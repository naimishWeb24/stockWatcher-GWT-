package com.google.gwt.sample.stockwatcher.client;

import java.util.Date;
import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;



public class StockWatcher implements EntryPoint {
	// widget declaration
	private static final int REFRESH_INTERVAL = 5000; // miliSeconds;
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable stockTable = new FlexTable();
	private Button addStockButton = 	new Button("Add");
	private TextBox newSymbolTextBox  =new TextBox();
	private Label lastUpdatedLabel = new Label();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private ArrayList<String> stocks = new ArrayList<String>();
	private StockPriceServiceAsync stockPriceSvc = GWT.create(StockPriceService.class);
	private Label errorMsgLabel = new Label();
	
	public void onModuleLoad() {
		//creating Table
		stockTable.setText(0,0,"Symbol");
		stockTable.setText(0,1,"Price");
		stockTable.setText(0,2,"Change");
		stockTable.setText(0,3,"Remove");
		stockTable.setCellPadding(6);

		// Add styles to elements in the stock list table.
		stockTable.getRowFormatter().addStyleName(0, "watchListHeader");
		stockTable.addStyleName("watchList");
		stockTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
		stockTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
		stockTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
	 
		// assemble add stock Panel
		addPanel.add(newSymbolTextBox);
		addPanel.add(addStockButton);
		addPanel.addStyleName("addPanel");
		
		errorMsgLabel.setStyleName("errorMessage");
		errorMsgLabel.setVisible(false);
		
		// assemble main Panel
		mainPanel.add(errorMsgLabel);
		mainPanel.add(stockTable);
		mainPanel.add(addPanel);
		mainPanel.add(lastUpdatedLabel);
		
		// associate the main Panel with HTML Page
		RootPanel.get("stockList").add(mainPanel);
		
		// Listen for mouse events on add Button
		addStockButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addStock();
			}
		});
		
		// Listen For KeyBoard event in inputBox(TextBox)
		newSymbolTextBox.addKeyDownHandler(new KeyDownHandler() {
	        public void onKeyDown(KeyDownEvent event) {
	          if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	            addStock();
	          }
	        }
		});
		
		// Move cursor Focus to the input box
		newSymbolTextBox.setFocus(true);
		
		// Setup timer to refresh list automatically.
	    Timer refreshTimer = new Timer() {
	      @Override
	      public void run() {
	        refreshWatchList();
	      }
	    };
	    refreshTimer.scheduleRepeating(REFRESH_INTERVAL);
	}
	
	// Validating Input in TextBox
	private void addStock() {
		final String symbol = newSymbolTextBox.getText().toUpperCase().trim();
		newSymbolTextBox.setFocus(true);
		
		if(!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
			Window.alert("'" + symbol + "' is not valid symbol");
			newSymbolTextBox.selectAll();
			return;
		}
		newSymbolTextBox.setText("");
		
		// Don't add the stock if it's already in the table.
		if(stocks.contains(symbol)) {
			return;
		}
		
		// Add the stock to the table
		int row = stockTable.getRowCount();
		stocks.add(symbol);
		stockTable.setText(row, 0, symbol);
		stockTable.setWidget(row, 2, new Label());
		stockTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
		stockTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
		stockTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
		
		// RemoveStockButton to remove this stock from the table
		Button removeStockButton = new Button("x");
		removeStockButton.addStyleDependentName("remove");
		removeStockButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int removedIndex = stocks.indexOf(symbol);
				stocks.remove(removedIndex);
				stockTable.removeRow(removedIndex + 1);
			}
		});
		stockTable.setWidget(row, 3, removeStockButton);
		refreshWatchList();
	}


	private void refreshWatchList() {
		if(stockPriceSvc == null) {
			stockPriceSvc = GWT.create(StockPriceService.class);
		}
		AsyncCallback<StockPrice[]> callback = new AsyncCallback<StockPrice[]>() {

			@Override
			public void onFailure(Throwable caught) {
				String details = caught.getMessage();
				if (caught instanceof DelistedException) {
					details = "Company '" + ((DelistedException) caught).getSymbol() + "' was delisted";
				}
				
				errorMsgLabel.setText("Error: " + details);
				errorMsgLabel.setVisible(true);
			}

			@Override
			public void onSuccess(StockPrice[] result) {
				updateTable(result);
				
			}
		};
		stockPriceSvc.getPrices(stocks.toArray(new String[0]), callback);
	}

	private void updateTable(StockPrice[] prices) {
		for (int i = 0; i < prices.length; i++) {
	        updateTable(prices[i]);
		}
		
		// Display timestamp showing last refresh
	     DateTimeFormat dateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_MEDIUM);
	     lastUpdatedLabel.setText("Last update : " + dateFormat.format(new Date())); 
	     
	  // Clear any errors.
	     errorMsgLabel.setVisible(false);
	}
	
	// lastUpdate
	private void updateTable(StockPrice price) {
		
		if(!stocks.contains(price.getSymbol())) {
			return;
		}
		
		int row = stocks.indexOf(price.getSymbol()) + 1;
		
		// Format the data in the price and change fields.
		String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
		NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
		String changeText = changeFormat.format(price.getChange());
		String changePercentText = changeFormat.format(price.getChangePercent());
		
		// populate the price and change field with new Data
		stockTable.setText(row, 1, priceText);
		Label changeWidget = (Label)stockTable.getWidget(row, 2);
		changeWidget.setText( changeText + "(" + changePercentText + " %)");
		
		// change the color in text when change value
		String changeStyleName = "noChange";
		if (price.getChangePercent() < -0.1f) {
		  changeStyleName = "negativeChange";
		}
		else if (price.getChangePercent() > 0.1f) {
		  changeStyleName = "positiveChange";
		}
		changeWidget.setStyleName(changeStyleName);
	}
}
	
	
	
	
	
