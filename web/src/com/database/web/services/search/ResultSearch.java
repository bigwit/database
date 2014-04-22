package com.database.web.services.search;

import com.database.web.services.search.ResultItem;

public class ResultSearch {
	
	private ResultItem[] resultset;
	
	private boolean errorMessage;
	
	public ResultSearch(int count) {
		if(count > 0)
			resultset = new ResultItem[count];
		else {
			this.errorMessage = true;
		}
	}
	
	public boolean isErrorMessage() {
		return this.errorMessage;
	}
	
	public int size() {
		if(resultset != null) {
			return resultset.length;
		}
		return 0;
	}
	
	public ResultItem[] set() {
		return this.resultset;
	}
	
	public void add(ResultItem item) {
		if(resultset != null && pointer < resultset.length) {
			resultset[pointer++] = item;
		}
	}
	
	int pointer = 0;

}
