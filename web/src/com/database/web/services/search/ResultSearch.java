package com.database.web.services.search;

import java.util.ArrayList;

import com.database.web.services.search.ResultItem;

public class ResultSearch {
	
	private ArrayList<ResultItem> resultset;
	
	private boolean errorMessage;
	
	public ResultSearch(int count) {
		if(count > 0)
			resultset = new ArrayList<>();
		else {
			this.errorMessage = true;
		}
	}
	
	public boolean isErrorMessage() {
		return this.errorMessage;
	}
	
	public ArrayList<ResultItem> set() {
		return this.resultset;
	}
	
	public void add(ResultItem item) {
		this.resultset.add(item);
	}
}
