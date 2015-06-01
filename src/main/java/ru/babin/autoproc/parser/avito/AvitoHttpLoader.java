package ru.babin.autoproc.parser.avito;

import java.util.LinkedList;
import java.util.List;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.loader.HttpLoader;
import ru.babin.autoproc.http.HttpRequester;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.parser.avito.converter.AvitoBodyTypeConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoBrandConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoCategoryConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoGearBoxTypeConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoMileAgeConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoModelConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoRegionConverter;

public class AvitoHttpLoader implements HttpLoader{
	
	public static final String PROVIDER_NAME = "AVITO";
	public static final String PROVIDER_SITE = "www.avito.ru";
		
	
	AvitoCategoryConverter catConverter = new AvitoCategoryConverter();
	AvitoRegionConverter regConverter = new AvitoRegionConverter();
	AvitoBrandConverter brandConverter = new AvitoBrandConverter();
	AvitoModelConverter modelConverter = new AvitoModelConverter();
	AvitoBodyTypeConverter bodyTypeConverter = new AvitoBodyTypeConverter();
	AvitoGearBoxTypeConverter gearBoxTypeConverter = new AvitoGearBoxTypeConverter();
	AvitoMileAgeConverter mileAgeConverter = new AvitoMileAgeConverter();
	
	HttpRequester httpRequester = new HttpRequester();
	
	public Response doRequest(AutoFilter filter) {
		String fullUrl = "http://" +  PROVIDER_SITE + 
				"/" + regConverter.convert(filter.getRegion()) +  
				"/" + catConverter.convert(filter.getCategory()) + 
				(filter.getBrand() != null ? "/" + brandConverter.convert(filter.getBrand()) : "") + 
				(filter.getModel() != null ? "/" + modelConverter.convert(filter.getModel()) : "") +
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
