package com.database.web.forms;

import org.springframework.stereotype.Component;

@Component
public class ViewSearchForm {
	
	private String query;
	
	private String type;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
