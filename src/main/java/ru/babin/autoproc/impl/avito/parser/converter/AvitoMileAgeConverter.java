package ru.babin.autoproc.impl.avito.parser.converter;

import ru.babin.autoproc.api.model.EMileAge;

public class AvitoMileAgeConverter {
	
	private final String FLAG_CODE = "1375"; 
	
	public String convert(int mileageFrom , int mileageTo){
		EMileAge from;
		EMileAge to;
		if(mileageFrom == 0){
			from = EMileAge.MA_0;
		}else{
			from = EMileAge.fromMiles(mileageFrom);
		}
		
		if(mileageTo == 0){
			to = EMileAge.MA_500;
		}else{
			to = EMileAge.fromMiles(mileageTo);
		}
		
		return FLAG_CODE + "_" + from.getCode() + "b" + to.getCode();		
	}
	
}
