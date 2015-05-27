package ru.babin.autoproc.parser.avito;

import ru.babin.autoproc.api.filter.Filter;
import ru.babin.autoproc.api.loader.Loader;
import ru.babin.autoproc.http.HttpRequester;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.parser.avito.converter.AvitoCategoryConverter;
import ru.babin.autoproc.parser.avito.converter.AvitoRegionConverter;

public class AvitoLoader implements Loader{

	private final String BASE_URL  = "http://www.avito.ru";
	
	AvitoCategoryConverter catConverter = new AvitoCategoryConverter();
	AvitoRegionConverter regConverter = new AvitoRegionConverter();
	
	HttpRequester httpRequester = new HttpRequester();
	
	public Response loadData(Filter filter) {
		String fullUrl = BASE_URL + "/" + 
				regConverter.convert(filter.getRegion()) +  "/" +   
				catConverter.convert(filter.getCategory())
				;
		
		return httpRequester.request(fullUrl); 
	}
	
	
	
}
