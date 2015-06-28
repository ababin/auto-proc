package ru.babin.autoproc.api.model;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

import ru.babin.autoproc.api.filter.AutoFilter;

public class WareList extends ArrayList <Ware>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalCount;
	
	private boolean loadingSkippedBySmallCount = false;
	private boolean loadingSkippedByBigCount = false;
	
	//private String loadedContent;
	
	private Document doc;
	
	private AutoFilter filter;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isLoadingSkippedBySmallCount() {
		return loadingSkippedBySmallCount;
	}

	public void setLoadingSkippedBySmallCount(boolean loadingSkippedBySmallCount) {
		this.loadingSkippedBySmallCount = loadingSkippedBySmallCount;
	}

	public boolean isLoadingSkippedByBigCount() {
		return loadingSkippedByBigCount;
	}

	public void setLoadingSkippedByBigCount(boolean loadingSkippedByBigCount) {
		this.loadingSkippedByBigCount = loadingSkippedByBigCount;
	}
	
	public boolean isLoadingSkipped(){
		return loadingSkippedBySmallCount || loadingSkippedByBigCount;
	}
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public AutoFilter getFilter() {
		return filter;
	}

	public void setFilter(AutoFilter filter) {
		this.filter = filter;
	}

	

	
	
	
}
