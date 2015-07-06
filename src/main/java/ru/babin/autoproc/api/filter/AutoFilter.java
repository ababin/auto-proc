package ru.babin.autoproc.api.filter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EGearBoxType;
import ru.babin.autoproc.api.model.EModel;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;

public class AutoFilter {
	
	private ERegion region;
	
	private ECategory category;
	
	private EBrand brand;
	private String brandVal;
	
	private Long markId;
	
	private EModel model;

	private List<EAutoBodyType> autoBodyTypes = new LinkedList <>();
	
	private Set<EGearBoxType> gearBoxTypes = new HashSet <>();
	
	private EPersonality personality = EPersonality.ALL; 
	
	private int priceFrom;
	
	private int priceTo;
	
	private int mileageFrom;
	
	private int mileageTo;
	
	private int yearFrom;
	
	private int yearTo;
	
	private EAgeType ageType = EAgeType.ALL;
	
	private int page = 1;
	
	private boolean needPhone = false;
	
	private int load_maxCount = Integer.MAX_VALUE;
	private int load_minCount = 0;
	
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

	public Set<EGearBoxType> getGearBoxTypes() {
		return gearBoxTypes;
	}

	public void addGearBoxType(EGearBoxType t) {
		gearBoxTypes.add(t);
	}

	public int getMileAgeFrom() {
		return mileageFrom;
	}

	public void setMileAgeFrom(int mileageFrom) {
		this.mileageFrom = mileageFrom;
	}

	public int getMileAgeTo() {
		return mileageTo;
	}

	public void setMileAgeTo(int mileageTo) {
		this.mileageTo = mileageTo;
	}
	
	public void setMileage(int mileageFrom, int mileageTo){
		this.mileageFrom = mileageFrom;
		this.mileageTo = mileageTo;
	}

	public int getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public int getYearTo() {
		return yearTo;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	
	public void setYear(int yearFrom , int yearTo){
		this.yearFrom = yearFrom;
		this.yearTo = yearTo;
	}

	public EAgeType getAgeType() {
		return ageType;
	}

	public void setAgeType(EAgeType ageType) {
		this.ageType = ageType;
	}

	public String getBrandVal() {
		return brandVal;
	}

	public void setBrandVal(String brandVal) {
		this.brandVal = brandVal;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public void incPage(){
		page++;
	}

	public boolean isNeedPhone() {
		return needPhone;
	}

	public void setNeedPhone(boolean needPhone) {
		this.needPhone = needPhone;
	}
	
	public int getLoad_maxCount() {
		return load_maxCount;
	}

	public void setLoad_maxCount(int load_maxCount) {
		this.load_maxCount = load_maxCount;
	}

	public int getLoad_minCount() {
		return load_minCount;
	}

	public void setLoad_minCount(int load_minCount) {
		this.load_minCount = load_minCount;
	}
	
	public boolean withoutPriceTo(){
		return priceTo == 0;
	}

	public Long getMarkId() {
		return markId;
	}

	public void setMarkId(Long markId) {
		this.markId = markId;
	}
		 
}
