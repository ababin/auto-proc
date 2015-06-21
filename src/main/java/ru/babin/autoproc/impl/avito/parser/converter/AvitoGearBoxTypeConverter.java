package ru.babin.autoproc.impl.avito.parser.converter;

import java.util.HashSet;
import java.util.Set;

import ru.babin.autoproc.api.model.EGearBoxType;

public class AvitoGearBoxTypeConverter {
	
	private static final String FLAG_CODE = "185";
	
	public String convert(Set <EGearBoxType> gearsIn){
		if(gearsIn == null || gearsIn.isEmpty()){
			return "";
		}
		Set <EGearBoxType> types = normalize(gearsIn);
		
		StringBuilder b = new StringBuilder();
		for(EGearBoxType type : types){
			b.append(b.length() == 0 ? getCode(type) : "-" + getCode(type));
		}
		return FLAG_CODE + "_" + b.toString();
	}
	
	private Set<EGearBoxType> normalize(Set<EGearBoxType> gearsIn) {
		Set <EGearBoxType> set = new HashSet<>();
		for(EGearBoxType gear : gearsIn){
			if(gear == EGearBoxType.AUTOMAT){
				set.add(EGearBoxType.AUTOMAT_AUTOMATIC);
				set.add(EGearBoxType.AUTOMAT_ROBOT);
				set.add(EGearBoxType.AUTOMAT_VARIATOR);
			}else{
				set.add(gear);
			}
		}
		return set;
	}

	public String getCode(EGearBoxType e){
		switch(e){
		case MECHANIC			:	return "861";
		case AUTOMAT_AUTOMATIC	:	return "860";
		case AUTOMAT_ROBOT		:	return "14754";
		case AUTOMAT_VARIATOR	:	return "14753";
		default 				:	throw new RuntimeException("Value " + e + " was not found for AvitoGearBoxTypeConverter !");	
		}
		
	}
	
}
