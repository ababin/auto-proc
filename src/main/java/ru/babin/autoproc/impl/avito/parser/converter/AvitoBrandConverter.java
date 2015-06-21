package ru.babin.autoproc.impl.avito.parser.converter;

import ru.babin.autoproc.api.model.EBrand;

public class AvitoBrandConverter {
	
	public String convert(EBrand brand){
		if(brand.name().startsWith("_")){
			return "";
		}
		switch(brand){
		case MERCEDES_BENZ	:	return EBrand.MERCEDES_BENZ.name().toLowerCase().replace("_", "-");
		default				:	return brand.name().toLowerCase();
		}
	}
	
}
