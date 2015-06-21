package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.PersonalityConverter;
import ru.babin.autoproc.api.model.EPersonality;

public class AutoruPersonalityConverter implements PersonalityConverter{

	@Override
	public String convert(EPersonality personality) {
		if(personality == null){
			personality = EPersonality.ALL;
		}
		
		switch(personality){
		case ALL	:	return "0";
		case AUTODEALER	:	return "2";
		case PRIVATE	:	return "1";
		default 		:	throw new RuntimeException("Value " + personality + " is not available for AutoruPersonalityConverter !");
		}
	}

}
