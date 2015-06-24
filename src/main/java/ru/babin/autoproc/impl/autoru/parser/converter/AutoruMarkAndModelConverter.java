package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.MarkAndModel;
import ru.babin.autoproc.api.model.Ware;

public class AutoruMarkAndModelConverter {
	
	private static final String [] COMPLEX_NAMES = new String[]{
		"alfa romeo", 
		"aston martin", 
		"great wall",
		"iran khodro",
		"land rover",
		"xin kai",
		"ваз (lada)"};
	
	public MarkAndModel convert(Ware w){
		String name = w.getParam(EParam.NAME);
		String mark = getMark(name);
		String model = name.substring(mark.length()).trim();
		return new MarkAndModel(mark, model);
		
	}
	
	private String getMark(String n){
		for(String complexName : COMPLEX_NAMES){
			if(n.toLowerCase().startsWith(complexName)){
				return n.substring(0, complexName.length()).trim();
			}
		}
		String [] ar = n.split(" ");
		return ar[0].trim();
	}
	
}
