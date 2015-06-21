package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.GearBoxTypeConverter;
import ru.babin.autoproc.api.model.EGearBoxType;

public class AutoruGearBoxTypeConverter implements GearBoxTypeConverter{

	@Override
	public String convert(EGearBoxType gear) {
		if(gear == null){
			return "";
		}
		switch(gear){
		case AUTOMAT			:	return "automatic_all";
		case AUTOMAT_AUTOMATIC	:	return "automatic_auto";
		case AUTOMAT_ROBOT		:	return "automatic_robot";
		case AUTOMAT_VARIATOR	:	return "automatic_variator";
		case MECHANIC			:	return "manual_all";
		default					:	throw new RuntimeException("Value " + gear + " is not available for AutoruGearBoxTypeConverter !");
		}
	}

}
