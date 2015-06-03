package ru.babin.autoproc.impl.avito.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.avito.parser.AvitoHttpLoader;

public class AutoModelsParser {
	
	private final String BRAND_AND_MODEL_SEPARATOR = "___";
	
	AvitoHttpLoader loader = new AvitoHttpLoader();
	
	public LinkedHashMap <String,String> getAutoModels(){
		LinkedHashMap <String,String> res = new LinkedHashMap<>();
		
		for(int i = 0; i < EBrand.values().length ; i++){
			System.out.println(i + " from " + EBrand.values().length);
			EBrand brand = EBrand.values()[i];
			res.putAll(getAutoModelsForBrand(brand));
		}
		
		return res;
	} 
	
	public LinkedHashMap <String,String> getAutoModelsForBrand(EBrand brand){
		AutoFilter f = new AutoFilter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		f.setBrand(brand);
		Response resp =  loader.doRequest(f);
		
		if(resp.code != 200){
			throw new RuntimeException("Error during load data for filter: " + f + " - HTTP status: " + resp.code);
		}
		List <String> rawStrings = prepareRawModels(resp.result); 
		return prepareEnumModels(brand, rawStrings);
	}
		
	private List <String> prepareRawModels(String strIn){
		List <String> res = new LinkedList<>();
		
		Document doc = Jsoup.parse(strIn);
		Elements elements = doc.select("option[data-filter-name=Модель]");
		if(elements.size() == 0){
			System.out.println("Can't find option[data-filter-name=Модель] ");
			return res;
		}
		Element select = elements.parents().get(0);
		for(int i = 0; i < select.children().size(); i++){
			Element el = select.children().get(i);
			if(!"".equals(el.attr("value"))){
				res.add(el.text());
			}
		}
		return res;
	}
	
	private LinkedHashMap<String,String> prepareEnumModels(EBrand brand , List<String> rawStrings) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		for(String str : rawStrings){
			map.put(brand.name() + BRAND_AND_MODEL_SEPARATOR +  processString(str),  str);
		}
		return map;
	}
	
	private String processString(String strIn){
		String str = strIn.replace("(", "");
		str = str.replace(")", "");
		// replace RUS 
		str = rusToEng(str.toLowerCase());
		str = str.replace(" ", "_");
		str = str.replace("/", "");
		str = str.replace("&", "");
		str = str.replace("'", "");
		str = str.replace("+", "");
		str = str.replace(".", "");
		return str;
	}
	
	
	// TRANSLITERATE -----------------------------------------------------
	private static final char[] abcCyr = {  'а','б','в','г','д','е','ё', 'ж', 'з','и','к','л','м','н', 'о','п','р','с','т','у','ф','х','ц','ч' , 'ш', 'щ', 'ы', 'э','ю', 'я'}; 
	private static final String[] abcLat = {"a","b","v","g","d","e","yo","zh","z","i","k","l","m","n", "o","p","r","s","t","u","f","h","ts","ch","sh","sch","y","e","yu","ya"}; 
	private String rusToEng(String text){
		StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < text.length(); i++) {
	        boolean found = false;
	    	for(int x = 0; x < abcCyr.length; x++ ){
	        	if (text.charAt(i) == abcCyr[x]) {
		            builder.append(abcLat[x]);
		            found = true;
		            break;
		        }
	        }
	        if(!found){
	        	builder.append(text.charAt(i));
	        }
	    }
	    return builder.toString();
	}
	//---------------------------------------------------------------------------
	
}

