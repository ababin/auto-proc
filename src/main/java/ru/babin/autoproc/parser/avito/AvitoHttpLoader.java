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
		
		// autoBodyTypes
		params.add(bodyTypeConverter.convert(filter.getAutoBodyTypes()));
		
		return splitParams(params);
	}

	private String splitParams(List<String> params) {
		StringBuilder b = new StringBuilder("?");
		boolean first = true;
		int counter = 0;
		for(String param : params){
			if(!param.isEmpty()){
				if(first){
					b.append(param);
					first = false;
				}else{
					b.append("&" + param);
				}
				counter ++;
			}
		}
		return counter == 0 ? "" : b.toString();
	}
	
	
	
}
