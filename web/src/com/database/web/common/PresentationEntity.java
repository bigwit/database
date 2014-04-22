package com.database.web.common;

import java.util.ArrayList;

public abstract class PresentationEntity {
	
	protected int count;
	
	
	protected ArrayList<Object[]> content;
	
	// n - количество столбцов
	public PresentationEntity(int n) {
		
	}
	
	public void addItem(Object[] item) {
		if(item.length == count) { 
			content.add(item);
		}
	}
	
	public int size() {
		return this.count;
	}
}
