package ru.babin.autoproc.parser.avito;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.api.filter.Filter;
import ru.babin.autoproc.api.model.Ware;

public class AvitoParser {
	
	AvitoLoader loader = new AvitoLoader();
	
	public List <Ware> parse(Filter f){
		Response resp = loader.loadData(f);
		
		Document doc = Jsoup.parse(resp.result);
		Elements elements = doc.select("");
		
		//return null;
	}
	
}
