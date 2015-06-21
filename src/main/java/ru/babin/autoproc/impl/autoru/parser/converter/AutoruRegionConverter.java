package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.RegionConverter;
import ru.babin.autoproc.api.model.ERegion;

public class AutoruRegionConverter implements RegionConverter{

	public String convert(ERegion ereg) {
		switch(ereg){
		case RUSSIA	:	return "rossiya";
		case MOSCOW	:	return "moscow";
		//case RUSSIA	:	return "";
		//case MOSCOW	:	return "87";
		}
		throw new RuntimeException("Value " + ereg + " is not available for AvitoRegionConverter !");
	}
	
}
