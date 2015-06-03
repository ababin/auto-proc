package ru.babin.autoproc.impl.avito.parser.converter;

import ru.babin.autoproc.api.model.EMileAge;

public class AvitoMileAgeConverter {
	
	private final String FLAG_CODE = "1375"; 
	
	public String convert(EMileAge mileageFrom , EMileAge mileageTo){
		if(mileageFrom == null){
			mileageFrom = EMileAge.MA_0;
		}
		if(mileageTo == null){
			mileageTo = EMileAge.MA_500;
		}
		
		return FLAG_CODE + "_" + mileageFrom.getCode() + "b" + mileageTo.getCode();		
	}
	
}
