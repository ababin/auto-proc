package ru.babin.autoproc.parser.avito.converter;

import ru.babin.autoproc.api.converter.RegionConverter;
import ru.babin.autoproc.api.model.ERegion;

public class AvitoRegionConverter implements RegionConverter{

	public String convert(ERegion ereg) {
		switch(ereg){
		case RUSSIA	:	return "rossiya";
		case MOSCOW	:	return "moskva";
		}
		throw new RuntimeException("Value " + ereg + " is not available for AvitoRegionConverter !");
	}
	
}
