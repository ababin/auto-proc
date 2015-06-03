package ru.babin.autoproc.api.filter;

import java.util.LinkedList;
import java.util.List;

import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EGearBoxType;
import ru.babin.autoproc.api.model.EMileAge;
import ru.babin.autoproc.api.model.EModel;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.EYear;

public class AutoFilter {
	
	private ERegion region;
	
	private ECategory category;
	
	private EBrand brand;
	
	private EModel model;

	private List<EAutoBodyType> autoBodyTypes = new LinkedList <>();
	
	private List<EGearBoxType> gearBoxTypes = new LinkedList <>();
	
	private EPersonality personality = EPersonality.ALL; 
	
	private int priceFrom;
	
	private int priceTo;
	
	private EMileAge mileageFrom = EMileAge.MA_0;
	
	private EMileAge mileageTo = EMileAge.MA_500;
	
	private EYear yearFrom = EYear.YEAR_1960;
	
	private EYear yearTo = EYear.YEAR_2015;
	
	private EAgeType ageType = null;
	
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

	public int getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}

	public int getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}
	
	public void setPrice(int priceForm, int priceTo){
		this.priceFrom = priceForm;
		this.priceTo = priceTo;
	}

	public List<EGearBoxType> getGearBoxTypes() {
		return gearBoxTypes;
	}

	public void addGearBoxType(EGearBoxType t) {
		gearBoxTypes.add(t);
	}

	public EMileAge getMileageFrom() {
		return mileageFrom;
	}

	public void setMileageFrom(EMileAge mileageFrom) {
		this.mileageFrom = mileageFrom;
	}

	public EMileAge getMileageTo() {
		return mileageTo;
	}

	public void setMileageTo(EMileAge mileageTo) {
		this.mileageTo = mileageTo;
	}
	
	public void setMileage(EMileAge mileageFrom, EMileAge mileageTo){
		this.mileageFrom = mileageFrom;
		this.mileageTo = mileageTo;
	}

	public EYear getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(EYear yearFrom) {
		this.yearFrom = yearFrom;
	}

	public EYear getYearTo() {
		return yearTo;
	}

	public void setYearTo(EYear yearTo) {
		this.yearTo = yearTo;
	}
	
	public void setYear(EYear yearFrom , EYear yearTo){
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
	}

	public EAgeType getAgeType() {
		return ageType;
	}

	public void setAgeType(EAgeType ageType) {
		this.ageType = ageType;
	}
	
}
