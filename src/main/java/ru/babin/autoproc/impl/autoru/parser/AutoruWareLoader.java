package ru.babin.autoproc.impl.autoru.parser;

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

public class AutoruWareLoader implements WareLoader{

	private AutoruHttpLoader httpLoader = new AutoruHttpLoader();
	
	private AutoruPhoneLoader phoneLoader = new AutoruPhoneLoader();
	
	@Override
	public List<Ware> load(AutoFilter filter) {
		return parse(filter);
	}

	private List <Ware> parse(AutoFilter f){
		Response resp = httpLoader.doRequest(f);
		String content = prepareContentForParse(resp.result);
		
		Document doc = Jsoup.parse(content);
		Elements elements = doc.select("table.sales-list-table");
		
		List <Ware> wares = new LinkedList<>();
		
		//for(int  i =0 ; i < elements.size(); i++){
		for(int  i =0 ; i < 3; i++){
			Element element = elements.get(i);
			//System.out.println(element.html());
			Ware ware = parseWare(element, f.getRegion());
			if(f.isNeedPhone()){
				phoneLoader.load(ware);
			}
			wares.add(ware);
		}
		
		return wares;
	}
	
	private String prepareContentForParse(String result) {
		String context = "<div class=\"widget widget_theme_white sales-list\">";
		int i = result.indexOf(context);
		if(i >= 0){
			return result.substring(i + context.length());
		}else{
			return result;
		}
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
		parseMileAge(ware, element);
		parseYear(ware, element);
		
		
		return ware;
	}
	
	private void setProviderParams(Ware ware){
		ware.addParam(EParam.PROVIDER_NAME, httpLoader.PROVIDER_NAME);
		ware.addParam(EParam.PROVIDER_SITE, httpLoader.PROVIDER_SITE);
	}
	
	private void parseDateStr(Ware ware , Element element){
		ware.addParam(EParam.DATE_STR, findValue(element, "div", "sales-list-date"));
	}
	
	private void parseCity(Ware ware , Element element, ERegion region){
		ware.addParam(EParam.PLACE, findValue(element, "div" , "sales-list-region ico-appear"));
	}
	
	private void parseMileAge(Ware ware , Element element){
		ware.addParam(EParam.MILE_AGE_STR, findValue(element, "td" , "sales-list-cell sales-list-cell_run"));
	}
	
	private void parseYear(Ware ware , Element element){
		ware.addParam(EParam.YEAR, findValue(element, "td" , "sales-list-cell sales-list-cell_year"));
	}
	
	private void parseAdsUrl(Ware ware , Element element){
		Element a = findElement(element, "a" , "sales-list-link");
		if(a != null){
			ware.addParam(EParam.ADS_URL, a.attr("href"));
		}
	}
	
	private void parseName(Ware ware , Element element){
		ware.addParam(EParam.NAME, getName(element));
	}
	
	private void parseAdsNumber(Ware ware , Element element){
		ware.addParam(EParam.ADS_NUMBER, findValue(element, "tr" , "sales-list-row" , "data-sale_id"));
	}
	
	private void parseImageUrl(Ware ware , Element element){
		String val = findValue(element, "img", "sales-list-thumb" , "src");
		if(val == null){
			val = findValue(element, "img", "sales-list-thumb sales-list-thumb_lazy" , "data-original");
		}
		ware.addParam(EParam.IMAGE_URL, val);
	}
	
	private void parseDescShort(Ware ware , Element element){
		String bodyTypeAndColor = getBodyTypeAndColor(element);
		String commonDesc = findValue(element, "td","sales-list-cell sales-list-cell_mark_id");
		String res = bodyTypeAndColor + " " + commonDesc;
		ware.addParam(EParam.DESC_SHORT, res);
	}
	
	private String getName(Element element){
		return findFullValue(element, "a","sales-list-link");
	}
	
	private String getBodyTypeAndColor(Element element){
		Element el = findElement(element , "div" , "body-type-text");
		Elements els = el.children();
		String color = els.get(0).text();
		String bodyType = els.get(1).text().toLowerCase();
		String res = color + " " + bodyType; 
		return res.trim();
	}
	
	private void parsePrice(Ware ware , Element element){
		ware.addParam(EParam.PRICE_STR, findFullValue(element, "div", "sales-list-price"));
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
	
	private String findFullValue(Element element, String tagName, String cssClass){
		Element el = findElement(element , tagName, cssClass);
		return el == null ? "" : el.text();
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
