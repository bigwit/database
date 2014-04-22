package com.database.web.services.search;

import com.database.web.common.SitePart;

public class ResultItem {
	
	private SitePart category;
	
	private String[] content;

	public SitePart getCategory() {
		return category;
	}

	public void setCategory(SitePart category) {
		this.category = category;
	}

	public String[] getContent() {
		return content;
	}

	public ResultItem(String[] contentLine) {
		this.content = contentLine;
	}
}
