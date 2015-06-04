package ru.babin.autoproc.impl.auto_ru.parser;

import java.util.LinkedList;
import java.util.List;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.loader.HttpLoader;
import ru.babin.autoproc.http.HttpRequester;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.auto_ru.parser.converter.AutoruRegionConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoAgeTypeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoBodyTypeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoBrandConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoCategoryConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoGearBoxTypeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoMileAgeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoModelConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoYearConverter;

public class AutoruHttpLoader implements HttpLoader{
	
	public static final String PROVIDER_NAME = "AUTO.RU";
	public static final String PROVIDER_SITE = "auto.ru";
		
	AutoruRegionConverter regConverter = new AutoruRegionConverter();
	
	AvitoCategoryConverter catConverter = new AvitoCategoryConverter();
	
	AvitoBrandConverter brandConverter = new AvitoBrandConverter();
	AvitoModelConverter modelConverter = new AvitoModelConverter();
	AvitoBodyTypeConverter bodyTypeConverter = new AvitoBodyTypeConverter();
	AvitoGearBoxTypeConverter gearBoxTypeConverter = new AvitoGearBoxTypeConverter();
	AvitoMileAgeConverter mileAgeConverter = new AvitoMileAgeConverter();
	AvitoYearConverter yearConverter = new AvitoYearConverter();
	AvitoAgeTypeConverter ageTypeConverter = new AvitoAgeTypeConverter();
	
	HttpRequester httpRequester = new HttpRequester();
	
	public Response doRequest(AutoFilter filter) {
		String fullUrl = "http://" +  
				
				regConverter.convert(filter.getRegion()) + "." + 
												
				PROVIDER_SITE +
				
				"/" + catConverter.convert(filter.getCategory()) +
				
				"/" + ageTypeConverter.convert(filter.getAgeType()) + 
				
				  
				"/?" + 
								
				prepareParams(filter)
				
				;
		
		return httpRequester.request(fullUrl); 
	}

	private String prepareParams(AutoFilter filter) {
		List <String> params = new LinkedList<>();				
		
		// personality
		params.add(filter.getPersonality().isAll() ? "" : "user=" + filter.getPersonality().getCode());
		
				
		// priceMin
		if(filter.getPriceFrom() > 0){
			params.add("pmin=" + filter.getPriceFrom());
		}
		
		// priceMax
		if(filter.getPriceTo() > 0){
			params.add("pmax=" + filter.getPriceTo());
		}
		
		// FLAG (f)
		params.add(prepareFLAGparam(filter));
				
		String splitParamsString = splitParams(params, "&"); 
		
		return splitParamsString.isEmpty() ? "" : "?"+splitParamsString;
	}
	
	private String prepareFLAGparam(AutoFilter filter){
		List <String> paramValues = new LinkedList<>();
		// autoBodyTypes
		paramValues.add(bodyTypeConverter.convert(filter.getAutoBodyTypes()));
		
		// gear-box type
		paramValues.add(gearBoxTypeConverter.convert(filter.getGearBoxTypes()));
		
		// mileAge parameters
		paramValues.add(mileAgeConverter.convert(filter.getMileageFrom(), filter.getMileageTo()));
		
		// year parameters
		paramValues.add(yearConverter.convert(filter.getYearFrom(), filter.getYearTo()));
		
		
		String splitParamValues = splitParams(paramValues, ".");
		
		return splitParamValues.isEmpty() ? "" : "f="+splitParamValues;
	}

	
	private String splitParams(List<String> params, String splitChar) {
		StringBuilder b = new StringBuilder();
		for(String param : params){
			if(!param.isEmpty()){
				b.append(b.length() == 0 ? param : splitChar+param);
			}
		}
		return b.toString();
	}
	
	
}
