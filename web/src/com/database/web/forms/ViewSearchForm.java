package com.database.web.forms;

import org.springframework.stereotype.Component;

@Component
public class ViewSearchForm {
	
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
