package ru.babin.autoproc.impl.avito.parser.converter;

import ru.babin.autoproc.api.converter.AgeTypeConverter;
import ru.babin.autoproc.api.model.EAgeType;

public class AvitoAgeTypeConverter implements AgeTypeConverter{

	@Override
	public String convert(EAgeType age) {
		if(age == null){
			return "";
		}
		switch(age){
		case NEW			:	return "novyy";
		case WITH_MILEAGE	:	return "s_probegom";
		case ALL			:	return ""; 
		}
		throw new RuntimeException("Value " + age + " is not available for AvitoAgeTypeConverter !");
	}

	
	
}
