package com.database.web.beans;

import org.springframework.stereotype.Component;

@Component
public class PageContextBean {
	
	private String title;
	
	private String encoding;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	
}
