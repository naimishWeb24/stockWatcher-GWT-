package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

public class DelistedException extends Exception implements Serializable {

	private String symbol;

	public DelistedException(String symbol) {
		this.symbol = symbol;
	}
	
	public DelistedException() {
		
	}
	
	public String getSymbol() {
	    return this.symbol;
	}
	
}
