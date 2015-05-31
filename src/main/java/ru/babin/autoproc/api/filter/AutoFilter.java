package ru.babin.autoproc.api.filter;

import java.util.LinkedList;
import java.util.List;

import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EModel;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;

public class AutoFilter {
	
	private ERegion region;
	
	private ECategory category;
	
	private EBrand brand;
	
	private EModel model;

	private List<EAutoBodyType> autoBodyTypes = new LinkedList <>();
	
	private EPersonality personality = EPersonality.ALL; 
	
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

	public EBrand getBrand() {
		return brand;
	}

	public void setBrand(EBrand brand) {
		this.brand = brand;
	}

	public EModel getModel() {
		return model;
	}

	public void setModel(EModel model) {
		this.model = model;
	}
	
	public void addAutoBodyType(EAutoBodyType abt){
		autoBodyTypes.add(abt);
	}
	
	public List <EAutoBodyType> getAutoBodyTypes(){
		return autoBodyTypes;
	}

	public EPersonality getPersonality() {
		return personality;
	}

	public void setPersonality(EPersonality personality) {
		this.personality = personality;
	}
	
}
