package ru.babin.autoproc.parser.avito.converter;

import java.util.List;

import ru.babin.autoproc.api.model.EAutoBodyType;

public class AvitoBodyTypeConverter {
	
	
	public String convert(List <EAutoBodyType> types){
		if(types.isEmpty()){
			return "";
		}
		StringBuilder b = new StringBuilder("f=187_");
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
