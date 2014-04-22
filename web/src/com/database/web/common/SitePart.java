package com.database.web.common;

public enum SitePart {
	
	CLIENTS("Мои путевки"),
	OFFICES("Офисы"),
	GID("Путеводитель"),
	NONE("Без категории");
	
	private String name;
	
	public String getDisplayName() {
		return this.name;
	}
	
	SitePart(String displayName) {
		this.name = displayName;
	}
}
