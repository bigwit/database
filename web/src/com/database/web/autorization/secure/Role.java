package com.database.web.autorization.secure;

public enum Role {

	ADMIN("ROOT"),
	EMPLOYEE("EMPLOYEE"),
	USER("USER");
	
	Role(String line) {
		this.inLine = line;
	}
	
	private String inLine;
	
	@Override
	public String toString() {
		return inLine;
	}
	
}
