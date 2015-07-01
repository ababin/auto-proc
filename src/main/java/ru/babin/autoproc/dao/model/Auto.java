package ru.babin.autoproc.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import ru.babin.autoproc.util.EqualsUtil;

@Entity
@Table(name = "AUTO")
public class Auto {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Column(name = "ID", nullable = false)
	private Long id;

	@javax.persistence.Column(name = "MARK", length = 50)
	private String mark;

	@javax.persistence.Column(name = "MARK_ID")
	private Long markId;

	@javax.persistence.Column(name = "MODEL", length = 50)
	private String model;

	@javax.persistence.Column(name = "MODEL_ID")
	private Long modelId;

	@javax.persistence.Column(name = "BODY_TYPE", length = 50)
	private String bodyType;

	@javax.persistence.Column(name = "BODY_TYPE_ID")
	private Long bodyTypeId;

	@javax.persistence.Column(name = "COLOR", length = 50)
	private String color;

	@javax.persistence.Column(name = "COLOR_ID", length = 50)
	private Long colorId;

	@javax.persistence.Column(name = "DRIVING_GEAR", length = 50)
	private String drivingGear;

	@javax.persistence.Column(name = "DRIVING_GEAR_ID")
	private Long drivingGearId;

	@javax.persistence.Column(name = "ENGINE_VOLUME", nullable = false)
	private int engineVolume;

	@javax.persistence.Column(name = "FUEL", length = 50)
	private String fuel;

	@javax.persistence.Column(name = "FUEL_ID")
	private Long fuelId;

	@javax.persistence.Column(name = "GEAR_BOX_TYPE", length = 50)
	private String gearBoxType;

	@javax.persistence.Column(name = "GEAR_BOX_TYPE_ID")
	private Long gearBoxTypeId;

	@javax.persistence.Column(name = "HORSES", nullable = false)
	private int horses;

	@javax.persistence.Column(name = "POWER", nullable = false)
	private int power;

	@javax.persistence.Column(name = "ADS_NUMBER", length = 50)
	private String adsNumber;

	@javax.persistence.Column(name = "ADS_URL", length = 255)
	private String adsUrl;

	@javax.persistence.Column(name = "IMAGE_URL", length = 255)
	private String imageUrl;

	@javax.persistence.Column(name = "PRICE", nullable = false)
	private int price;

	@javax.persistence.Column(name = "DATE_TIME", nullable = false)
	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date date;

	@javax.persistence.Column(name = "CITY", length = 100)
	private String city;

	@javax.persistence.Column(name = "CITY_ID")
	private Long cityId;

	@javax.persistence.Column(name = "MILE_AGE", nullable = false)
	private int mileAge;

	@javax.persistence.Column(name = "YEAR", nullable = false)
	private int year;

	@javax.persistence.Column(name = "PROVIDER", length = 100)
	private String provider;

	@javax.persistence.Column(name = "PROVIDER_ID", nullable = false)
	private Long providerId;
	
	@javax.persistence.Column(name = "WHEEL", length = 50)
	private String wheel;
	
	@javax.persistence.Column(name = "STATE", length = 50)
	private String state;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Long getMarkId() {
		return markId;
	}

	public void setMarkId(Long markId) {
		this.markId = markId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Long getBodyTypeId() {
		return bodyTypeId;
	}

	public void setBodyTypeId(Long bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public String getDrivingGear() {
		return drivingGear;
	}

	public void setDrivingGear(String drivingGear) {
		this.drivingGear = drivingGear;
	}

	public Long getDrivingGearId() {
		return drivingGearId;
	}

	public void setDrivingGearId(Long drivingGearId) {
		this.drivingGearId = drivingGearId;
	}

	public int getEngineVolume() {
		return engineVolume;
	}

	public void setEngineVolume(int engineVolume) {
		this.engineVolume = engineVolume;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public Long getFuelId() {
		return fuelId;
	}

	public void setFuelId(Long fuelId) {
		this.fuelId = fuelId;
	}

	public String getGearBoxType() {
		return gearBoxType;
	}

	public void setGearBoxType(String gearBoxType) {
		this.gearBoxType = gearBoxType;
	}

	public Long getGearBoxTypeId() {
		return gearBoxTypeId;
	}

	public void setGearBoxTypeId(Long gearBoxTypeId) {
		this.gearBoxTypeId = gearBoxTypeId;
	}

	public int getHorses() {
		return horses;
	}

	public void setHorses(int horses) {
		this.horses = horses;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getAdsNumber() {
		return adsNumber;
	}

	public void setAdsNumber(String adsNumber) {
		this.adsNumber = adsNumber;
	}

	public String getAdsUrl() {
		return adsUrl;
	}

	public void setAdsUrl(String adsUrl) {
		this.adsUrl = adsUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public int getMileAge() {
		return mileAge;
	}

	public void setMileAge(int mileAge) {
		this.mileAge = mileAge;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
	
	public String getWheel() {
		return wheel;
	}

	public void setWheel(String wheel) {
		this.wheel = wheel;
	}
	
	public boolean equalsWithoutId(Auto a){
		
		return EqualsUtil.equals(a.getAdsNumber(), adsNumber) &&
				EqualsUtil.equals(a.getAdsUrl(), adsUrl) &&
				EqualsUtil.equals(a.getBodyType(), bodyType) &&
				EqualsUtil.equals(a.getBodyTypeId(), bodyTypeId) &&
				EqualsUtil.equals(a.getCity(), city) &&
				EqualsUtil.equals(a.getCityId(), cityId) &&
				EqualsUtil.equals(a.getColor(), color) &&
				EqualsUtil.equals(a.getColorId(), colorId) &&
				EqualsUtil.equals(a.getDate(), date) &&
				EqualsUtil.equals(a.getDrivingGear(), drivingGear) &&
				EqualsUtil.equals(a.getDrivingGearId(), drivingGearId) &&
				EqualsUtil.equals(a.getEngineVolume(), engineVolume) &&
				EqualsUtil.equals(a.getFuel() , fuel) &&
				EqualsUtil.equals(a.getFuelId(), fuelId) &&
				EqualsUtil.equals(a.getGearBoxType(), gearBoxType) &&
				EqualsUtil.equals(a.getGearBoxTypeId(), gearBoxTypeId) &&
				EqualsUtil.equals(a.getHorses(), horses) &&
				EqualsUtil.equals(a.getImageUrl(), imageUrl) &&
				EqualsUtil.equals(a.getMark(), mark) &&
				EqualsUtil.equals(a.getMarkId() , markId) &&
				EqualsUtil.equals(a.getMileAge(), mileAge) &&
				EqualsUtil.equals(a.getModel() , model) &&
				EqualsUtil.equals(a.getModelId() , modelId) &&
				EqualsUtil.equals(a.getPower(), power) &&
				EqualsUtil.equals(a.getPrice() , price) &&
				EqualsUtil.equals(a.getProvider(), provider) &&
				EqualsUtil.equals(a.getProviderId() , providerId) &&
				EqualsUtil.equals(a.getYear() , year) &&
				EqualsUtil.equals(a.getWheel() , wheel) &&
				EqualsUtil.equals(a.getState() , state);
		
	}
	
	public String toString(){
		return this.getClass().getSimpleName() + ":{" + 
				"id=" + id + "; " + 
				"mark=" + mark + "; " + 
				"markId=" + markId + "; " + 
				"model=" + model + "; " + 
				"modelId=" + modelId + "; " + 
				"bodyType=" + bodyType + "; " + 
				"bodyTypeId=" + bodyTypeId + "; " + 
				"color=" + color + "; " + 
				"colorId=" + colorId + "; " +
				"wheel=" + wheel + "; " +
				"drivingGear=" + drivingGear + "; " + 
				"drivingGeearId=" + drivingGearId + "; " + 
				"engineVolume=" + engineVolume + "; " + 
				"fuel=" + fuel + "; " + 
				"fuelId=" + fuelId + "; " + 
				"gearBoxType=" + gearBoxType + "; " + 
				"gearBoxTypeId=" + gearBoxTypeId + "; " + 
				"horses=" + horses + "; " + 
				"power=" + power + "; " + 
				"adsNumber=" + adsNumber + "; " + 
				"adsUrl=" + adsUrl + "; " + 
				"imageUrl=" + imageUrl + "; " + 
				"price=" + price + "; " + 
				"state=" + state + "; " +
				"dateTime=" + date + "; " + 
				"city=" + city + "; " + 
				"cityId=" + cityId + "; " +
				"mileAge=" + mileAge + "; " + 
				"year=" + year + "; " + 
				"provider=" + provider + "; " + 
				"providerId=" + providerId + "; " +
				"}";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
