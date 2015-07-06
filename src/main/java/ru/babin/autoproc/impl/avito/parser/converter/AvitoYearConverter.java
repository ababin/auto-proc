package ru.babin.autoproc.impl.avito.parser.converter;

import ru.babin.autoproc.api.model.EYear;

public class AvitoYearConverter {
	
	private final String FLAG_CODE = "188"; 
	
	public String convert(int yearFrom , int yearTo){
		EYear from = null;
		EYear to = null;
		if(yearFrom == 0){
			from = EYear.YEAR_1960;
		}else{
			from = EYear.fromYear(yearFrom);
		}
		if(yearTo == 0){
			to = EYear.YEAR_2015;
		}else{
			to = EYear.fromYear(yearTo);
		}
		
		return FLAG_CODE + "_" + from.getCode() + "b" + to.getCode();		
	}
	
}
