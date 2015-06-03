package ru.babin.autoproc.impl.avito.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.avito.parser.AvitoHttpLoader;

public class AutoBrandsParser {
	
	AvitoHttpLoader loader  = new AvitoHttpLoader();
	
	public LinkedHashMap <String,String> getAutoCats(){
		AutoFilter f = new AutoFilter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		
		Response resp = loader.doRequest(f);
		if(resp.code != 200){
			throw new RuntimeException("Error during load filter " + f + " - HTTP status: " + resp.code);
		}
		List <String> rawStrings = prepareRawCats(resp.result); 
		return prepareEnumCats(rawStrings);
	}
		
	private List <String> prepareRawCats(String strIn){
		Document doc = Jsoup.parse(strIn);
		Elements elements = doc.select("select#flt_param_210 option");
		List <String> res = new LinkedList<>();
		for(int i = 0; i < elements.size(); i++){
			Element el = elements.get(i);
			if(!"".equals(el.attr("value"))){
				//System.out.println(el.text());
				res.add(el.text());
			}
		}
		return res;
	}
	
	private LinkedHashMap<String,String> prepareEnumCats(List<String> rawStrings) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		for(String str : rawStrings){
			map.put(processString(str),  str);
		}
		return map;
	}
	
	private String processString(String strIn){
		String str = strIn.replace("(", "");
		str = str.replace(")", "");
		// replace RUS 
		str = Transliterator.toTranslit(str.toLowerCase());
		str = str.replace(" ", "_");
		str = str.replace("-", "_");
		return str;
	}
				
}
