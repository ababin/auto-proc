package ru.babin.autoproc.api.filter;

import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.ERegion;

public class Filter {
	
	private ERegion region;
	
	private ECategory category;

	public ERegion getRegion() {
		return region;
	}

	public void setRegion(ERegion region) {
		this.region = region;
	}

	public ECategory getCategory() {
		return category;
	}

	public void setCategory(ECategory category) {
		this.category = category;
	}
	
	
	
}
