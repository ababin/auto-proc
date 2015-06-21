package ru.babin.autoproc.impl.autoru.parser;

import java.util.LinkedList;
import java.util.List;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.loader.HttpLoader;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EGearBoxType;
import ru.babin.autoproc.http.HttpRequester;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruAgeTypeConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruAutoBodyConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruBrandConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruCategoryConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruGearBoxTypeConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruPersonalityConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruRegionConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoBodyTypeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoGearBoxTypeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoMileAgeConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoModelConverter;
import ru.babin.autoproc.impl.avito.parser.converter.AvitoYearConverter;

public class AutoruHttpLoader implements HttpLoader{
	
	public static final String PROVIDER_NAME = "AUTO.RU";
	public static final String PROVIDER_SITE = "auto.ru";
		
	AutoruRegionConverter regConverter = new AutoruRegionConverter();
	AutoruCategoryConverter catConverter = new AutoruCategoryConverter();
	AutoruAgeTypeConverter ageTypeConverter = new AutoruAgeTypeConverter();
	AutoruPersonalityConverter personalityConverter = new AutoruPersonalityConverter();
	AutoruAutoBodyConverter bodyConverter = new AutoruAutoBodyConverter();
	AutoruBrandConverter brandConverter = new AutoruBrandConverter();
	AutoruGearBoxTypeConverter gearBoxConverter = new AutoruGearBoxTypeConverter();
	
	AvitoModelConverter modelConverter = new AvitoModelConverter();
	AvitoBodyTypeConverter bodyTypeConverter = new AvitoBodyTypeConverter();
	AvitoGearBoxTypeConverter gearBoxTypeConverter = new AvitoGearBoxTypeConverter();
	AvitoMileAgeConverter mileAgeConverter = new AvitoMileAgeConverter();
	AvitoYearConverter yearConverter = new AvitoYearConverter();
	
	
	HttpRequester httpRequester = new HttpRequester();
	
	public Response doRequest(AutoFilter filter) {
		String fullUrl = "http://" +  
				
				regConverter.convert(filter.getRegion()) + "." + 
												
				PROVIDER_SITE +
				
				"/" + catConverter.convert(filter.getCategory()) +
				
				"/" + ageTypeConverter.convert(filter.getAgeType()) + 
				
						 
								
				prepareParams(filter)
				
				;
		
		System.out.println("URL : " + fullUrl);
		
		return httpRequester.request(fullUrl); 
	}

	private String prepareParams(AutoFilter filter) {
		List <String> params = new LinkedList<>();				
		
		// country_group_id - 
		params.add("search[country_group_id][5]=1");
		
		// region (city)
		//params.add("search[geo_region]=" + regConverter.convert(filter.getRegion()));
		
		// markfolder
		params.add("search[mark-folder][]=0-");
		
		// personality
		params.add("search[seller]=" + personalityConverter.convert(filter.getPersonality()));  
				
		// priceMin
		if(filter.getPriceFrom() > 0){
			params.add("search[price][min]=" + filter.getPriceFrom());
		}
		
		// priceMax
		if(filter.getPriceTo() > 0){
			params.add("search[price][max]=" + filter.getPriceTo());
		}
		
		// yearFrom
		params.add("search[year][min]=" + filter.getYearFrom().getYear());
		
		// yearTo
		params.add("search[year][max]=" + filter.getYearTo().getYear());
		
		// body
		for(EAutoBodyType body : filter.getAutoBodyTypes()){
			params.add("search[body_type][" + bodyConverter.convert(body) + "]=1");
		}
		
		// brand
		if(filter.getBrand() != null){
			params.add("search[mark][]=" + brandConverter.convert(filter.getBrand()));
		}
		
		// gearBox
		if(!filter.getGearBoxTypes().isEmpty()){
			for(EGearBoxType gear : filter.getGearBoxTypes()){
				params.add("search[gearbox][" + gearBoxConverter.convert(gear) + "]=1");
			}
		}
				
		//run
		if(filter.getMileAgeFrom() != null){
			params.add("search[run][min]=" + filter.getMileAgeFrom().getMiles());
		}
		if(filter.getMileageTo() != null){
			params.add("search[run][max]=" + filter.getMileageTo().getMiles());
		}
			
		// sort
		params.add("sort[set_date]=desc");
		
		
		String splitParamsString = splitParams(params, "&"); 
		
		return splitParamsString.isEmpty() ? "" : "?"+splitParamsString;
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
