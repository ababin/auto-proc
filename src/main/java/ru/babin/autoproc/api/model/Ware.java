package ru.babin.autoproc.api.model;

import java.util.LinkedList;
import java.util.List;

public class Ware {
	
	private List <Param> params = new LinkedList <>();
	
	public String getParam(EParam paramIn){
		for(Param param : params){
			if(param.param == paramIn){
				return param.value;
			}
		}
		return null;
	}

	public void addParam(EParam eparam , String value){
		Param p = new Param(eparam, value);
		params.add(p);
	}
	
	public String getName(){
		return getParam(EParam.NAME);
	}
	
	public String getPrice(){
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
	
	public String getYear(){
		return getParam(EParam.YEAR);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName() + "{");
		boolean first = true;
		for(Param p : params){
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
