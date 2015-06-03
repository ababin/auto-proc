package ru.babin.autoproc.impl.avito.parser.converter;

import java.util.List;

import ru.babin.autoproc.api.model.EGearBoxType;

public class AvitoGearBoxTypeConverter {
	
	private static final String FLAG_CODE = "185";
	
	public String convert(List <EGearBoxType> types){
		if(types.isEmpty()){
			return "";
		}
		StringBuilder b = new StringBuilder(FLAG_CODE + "_");
		boolean first = true;
		for(EGearBoxType type : types){
			if(first){
				b.append(type.getId());
				first = false;
			}else{
				b.append("-" + type.getId());
			}
		}
		return b.toString();
	}
	
}
