package ru.babin.autoproc.impl.autoru.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.babin.autoproc.http.HttpRequester;
import ru.babin.autoproc.http.Response;

public class AutoruParamsLoader {
	
	private final String BRANDS_URL = "http://moscow.auto.ru/cars/all/?advanced_search=1";
	
	private Map<String,String> brands = new LinkedHashMap<>();
			
	public Map <String, String> getBrands(){
		if(!brands.isEmpty()){
			return brands;
		}
		synchronized (brands){
			if(!brands.isEmpty()){
				return brands;
			}
			loadBrands();			
		}
		return brands;
	}
	
	private void loadBrands(){
		HttpRequester requester = new HttpRequester();
		Response resp = requester.request(BRANDS_URL);
		if(resp.isOK()){
			Document doc = Jsoup.parse(resp.result);
			Elements elements = doc.select("div.select_role_mark");
			if(elements.size() != 1){
				return;
			}			
			Element el = elements.first();
			for(Element item : el.getElementsByAttributeValueStarting("class", "menu-item")){
				String name = item.text();
				String attr = item.attr("data-bem");
				attr  = attr.substring(0, attr.length()-2);
				String [] ar = attr.split("\"val\":");
				System.out.println(name + " ==== " + ar[1]);
				
				brands.put(ar[1], name);
				
			}
			
		}
	}
	
}
