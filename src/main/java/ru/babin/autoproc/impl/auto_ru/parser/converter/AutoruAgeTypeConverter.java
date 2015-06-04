package ru.babin.autoproc.impl.auto_ru.parser.converter;

import ru.babin.autoproc.api.converter.AgeTypeConverter;
import ru.babin.autoproc.api.model.EAgeType;

public class AutoruAgeTypeConverter implements AgeTypeConverter{

	@Override
	public String convert(EAgeType age) {
		if(age == null){
			return "";
		}
		switch(age){
		case NEW			:	return "new";
		case WITH_MILEAGE	:	return "used";
		case ALL			:	return "all"; 
		}
		throw new RuntimeException("Value " + age + " is not available for AutoruAgeTypeConverter !");
	}

	
	
}
