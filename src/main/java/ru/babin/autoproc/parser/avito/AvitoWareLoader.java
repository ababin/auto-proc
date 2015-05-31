package ru.babin.autoproc.parser.avito;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.loader.WareLoader;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.http.Response;

public class AvitoWareLoader implements WareLoader{

	private AvitoHttpLoader httpLoader = new AvitoHttpLoader();
	
	@Override
	public List<Ware> load(AutoFilter filter) {
		return parse(filter);
	}

	private List <Ware> parse(AutoFilter f){
		Response resp = httpLoader.doRequest(f);
		
		Document doc = Jsoup.parse(resp.result);
		Elements elements = doc.select("div.item");
		
		List <Ware> wares = new LinkedList<>();
		
		for(int  i =0 ; i < elements.size(); i++){
			Element element = elements.get(i);
			//System.out.println(element.html());
			wares.add(parseWare(element, f.getRegion()));
		}
		
		return wares;
	}
	
	private Ware parseWare(Element element, ERegion region){
		Ware ware = new Ware();
		
		setProviderParams(ware);
		
		parseAdsUrl(ware , element);
		parseImageUrl(ware, element);
		parseDescShort(ware, element);
		parsePrice(ware, element);
		parseDateStr(ware, element);
		parseCity(ware, element, region);
		parseName(ware, element);
		parseAdsNumber(ware, element);
		
		return ware;
	}
	
	private void setProviderParams(Ware ware){
		ware.addParam(EParam.PROVIDER_NAME, AvitoHttpLoader.PROVIDER_NAME);
		ware.addParam(EParam.PROVIDER_SITE, AvitoHttpLoader.PROVIDER_SITE);
	}
	
	private void parseDateStr(Ware ware , Element element){
		ware.addParam(EParam.DATE_STR, findValue(element, "div", "date c-2"));
	}
	
	private void parseCity(Ware ware , Element element, ERegion region){
		Element div = findElement(element, "div", "data");
		if(div != null && div.children().size() > 0){
			Element p_1 = div.child(1);
			if(p_1.tagName().equals("p")){
				ware.addParam(EParam.CITY, p_1.ownText());
			}else{
				ware.addParam(EParam.CITY, region.getReadableName());
			}
		}
	}
	
	private void parseAdsUrl(Ware ware , Element element){
		Element h3 = findElement(element, "h3" , "title");
		if(h3 != null){
			ware.addParam(EParam.ADS_URL, findValue(h3, "a" , "" , "href"));
		}
	}
	
	private void parseName(Ware ware , Element element){
		Element h3 = findElement(element, "h3" , "title");
		if(h3 != null){
			ware.addParam(EParam.NAME, h3.text());
		}
	}
	
	private void parseAdsNumber(Ware ware , Element element){
		String url = ware.getParam(EParam.ADS_URL);
		if(url != null){
			String [] ar = url.split("_");
			ware.addParam(EParam.ADS_NUMBER, ar[ar.length-1]);
		}
	}
	
	private void parseImageUrl(Ware ware , Element element){
		ware.addParam(EParam.IMAGE_URL, findValue(element, "img", "photo-count-show" , "src"));
	}
	
	private void parseDescShort(Ware ware , Element element){
		ware.addParam(EParam.DESC_SHORT, findValue(element, "span", "param"));
	}
	
	private void parsePrice(Ware ware , Element element){
		ware.addParam(EParam.PRICE_STR, findValue(element, "div", "about"));
	}
	
	private String findValue(Element element, String tagName, String cssClass, String attrName){
		Elements els = element.getElementsByTag(tagName);
		if(els != null && els.size() > 0){
			for(int i = 0; i < els.size(); i++){
				Element el = els.get(i);
				if(cssClass.equals(el.className())){
					return el.attr(attrName);
				}
			}
		}
		return null;
	}
	
	private String findValue(Element element, String tagName, String cssClass){
		Elements els = element.getElementsByTag(tagName);
		if(els != null && els.size() > 0){
			for(int i = 0; i < els.size(); i++){
				Element el = els.get(i);
				if(cssClass.equals(el.className())){
					return el.ownText();
				}
			}
		}
		return null;
	}
	
	private Element findElement(Element element, String tagName, String cssClass){
		Elements els = element.getElementsByTag(tagName);
		if(els != null && els.size() > 0){
			for(int i = 0; i < els.size(); i++){
				Element el = els.get(i);
				if(cssClass.equals(el.className())){
					return el;
				}
			}
		}
		return null;
	}
	
	
}
