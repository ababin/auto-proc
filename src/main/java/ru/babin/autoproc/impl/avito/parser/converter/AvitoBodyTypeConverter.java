package ru.babin.autoproc.impl.avito.parser.converter;

import java.util.List;

import ru.babin.autoproc.api.model.EAutoBodyType;

public class AvitoBodyTypeConverter {
	
	private static final String FLAG_CODE = "187";
	
	public String convert(List <EAutoBodyType> types){
		if(types.isEmpty()){
			return "";
		}
		StringBuilder b = new StringBuilder(FLAG_CODE + "_");
		boolean first = true;
		for(EAutoBodyType type : types){
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
