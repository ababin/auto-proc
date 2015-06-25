package ru.babin.autoproc.api.model;

public class AutoDesc {
	
	private String color;
	
	private String bodyType;
	
	private int enginVolume;
	
	private String gearBoxType;
	
	/**
	 * л.с.
	 */
	private int horses;
	
	/**
	 * кВт
	 */
	private int power;
	
	private String fuel;
	
	/*
	 * привод
	 */
	private String drivingGear;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public int getEngineVolume() {
		return enginVolume;
	}

	public void setEngineVolume(int enginVolume) {
		this.enginVolume = enginVolume;
	}

	public String getGearBoxType() {
		return gearBoxType;
	}

	public void setGearBoxType(String gearBoxType) {
		this.gearBoxType = gearBoxType;
	}

	public int getHorses() {
		return horses;
	}

	public void setHorses(int horses) {
		this.horses = horses;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getDrivingGear() {
		return drivingGear;
	}

	public void setDrivingGear(String drivingGear) {
		this.drivingGear = drivingGear;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	
	
}
