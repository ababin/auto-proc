package ru.babin.autoproc.api.model;

import java.util.ArrayList;

public class WareList extends ArrayList <Ware>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalCount;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
