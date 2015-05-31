package ru.babin.autoproc.parser.avito.converter;

import ru.babin.autoproc.api.model.EBrand;

public class AvitoBrandConverter {
	
	public String convert(EBrand brand){
		switch(brand){
		case MERCEDES_BENZ	:	return EBrand.MERCEDES_BENZ.name().toLowerCase().replace("_", "-");
		default				:	return brand.name().toLowerCase();
		}
	}
	
}
