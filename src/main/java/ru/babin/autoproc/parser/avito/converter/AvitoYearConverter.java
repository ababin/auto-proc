package ru.babin.autoproc.parser.avito.converter;

import ru.babin.autoproc.api.model.EMileAge;
import ru.babin.autoproc.api.model.EYear;

public class AvitoYearConverter {
	
	private final String FLAG_CODE = "188"; 
	
	public String convert(EYear yearFrom , EYear yearTo){
		if(yearFrom == null){
			yearFrom = EYear.YEAR_1960;
		}
		if(yearTo == null){
			yearTo = EYear.YEAR_2015;
		}
		
		return FLAG_CODE + "_" + yearFrom.getCode() + "b" + yearTo.getCode();		
	}
	
}
