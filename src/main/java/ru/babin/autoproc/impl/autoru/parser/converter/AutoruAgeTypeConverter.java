package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.AgeTypeConverter;
import ru.babin.autoproc.api.model.EAgeType;

public class AutoruAgeTypeConverter implements AgeTypeConverter{

	@Override
	public String convert(EAgeType age) {
		if(age == null){
			age = EAgeType.ALL;
		}
		switch(age){
		case NEW			:	return "new";
		case WITH_MILEAGE	:	return "used";
		case ALL			:	return "all"; 
		}
		throw new RuntimeException("Value " + age + " is not available for AutoruAgeTypeConverter !");
	}

	
	
}
