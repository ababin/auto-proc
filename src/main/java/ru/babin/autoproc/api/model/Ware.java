package ru.babin.autoproc.api.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Ware {
	
	private Map <EParam, Param> params = new HashMap <>();
	
	public String getParam(EParam paramIn){
		Param param = params.get(paramIn);
		return param == null ? null : param.value;
	}

	public void addParam(EParam eparam , String value){
		Param p = new Param(eparam, value);
		params.put(eparam,p);
	}
	
	public String getName(){
		return getParam(EParam.NAME);
	}
	
	public String getPriceStr(){
		return getParam(EParam.PRICE_STR);
	}
	
	public String getDescShort(){
		return getParam(EParam.DESC_SHORT);
	}
	
	public String getImageUrl(){
		return getParam(EParam.IMAGE_URL);
	}
	
	public String getAdsUrl(){
		return getParam(EParam.ADS_URL);
	}
	
	public String getProviderSite(){
		return getParam(EParam.PROVIDER_SITE);
	}
	
	public String getDateStr(){
		return getParam(EParam.DATE_STR);
	}
	
	public String getMileAgeStr(){
		return getParam(EParam.MILE_AGE_STR);
	}
	
	public String getYearStr(){
		return getParam(EParam.YEAR);
	}
	
	public String getMark(){
		return getParam(EParam.MARK);
	}
	
	public String getModel(){
		return getParam(EParam.MODEL);
	}
	
	public String getBodyType(){
		return getParam(EParam.BODY_TYPE);
	}
	
	public String getPlace(){
		return getParam(EParam.PLACE);
	}
	
	public String getColor(){
		return getParam(EParam.COLOR);
	}
	
	public String getDrivingGear(){
		return getParam(EParam.DRIVING_GEAR);
	}
	
	public String getFuel(){
		return getParam(EParam.FUEL);
	}
	
	public String getGearBox(){
		return getParam(EParam.GEAR_BOX_TYPE);
	}
	
	public String getProviderName(){
		return getParam(EParam.PROVIDER_NAME);
	}
	
	public String getAdsNumber(){
		return getParam(EParam.ADS_NUMBER);
	}
	
	public Date getDate(){
		String s = getParam(EParam.DATE);
		return new Date(Long.valueOf(s));  
	}
	
	public int getEngineVolume(){
		return Integer.valueOf(getParam(EParam.ENGINE_VOLUME));
	}
	
	public int getHorses(){
		String s = getParam(EParam.HORSES);
		return s == null ? 0 : Integer.valueOf(s);
	}
	
	public int getPower(){
		String s = getParam(EParam.POWER);
		return s == null ? 0 : Integer.valueOf(s);
	}
	
	public int getMileAge(){
		String s = getParam(EParam.MILE_AGE);
		return s == null ? 0 : Integer.valueOf(s);
	}
	
	public int getPrice(){
		String s = getParam(EParam.PRICE);
		return s == null ? 0 : Integer.valueOf(s);
	}
	
	public int getYear(){
		String s = getParam(EParam.YEAR);
		return s == null ? 0 : Integer.valueOf(s);
	}
	
	public String getWheel(){
		return getParam(EParam.WHEEL);
	}
	
	public String getState(){
		return getParam(EParam.STATE);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName() + "{");
		boolean first = true;
		for(Param p : params.values()){
			if(first){
				sb.append(p);
				first = false;
			}else{
				sb.append(", " + p);
			}
		}
		sb.append("}");
		return sb.toString(); 
	}
	
}
