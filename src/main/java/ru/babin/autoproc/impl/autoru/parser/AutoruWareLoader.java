package ru.babin.autoproc.impl.autoru.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.loader.WareLoader;
import ru.babin.autoproc.api.model.AutoDesc;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.MarkAndModel;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.api.model.WareList;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruAutoDescConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruDateConverter;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruMarkAndModelConverter;

public class AutoruWareLoader implements WareLoader{

	private AutoruHttpLoader httpLoader = new AutoruHttpLoader();
	
	private AutoruPhoneLoader phoneLoader = new AutoruPhoneLoader();
	
	private AutoruDateConverter dateConverter = new AutoruDateConverter();
	
	private AutoruMarkAndModelConverter markAndModelConverter = new AutoruMarkAndModelConverter();
	
	private AutoruAutoDescConverter autoDescConverter = new AutoruAutoDescConverter();
	
	@Override
	public WareList load(AutoFilter filter) {
		return parse(filter);
	}

	private WareList parse(AutoFilter f){
		Response resp = httpLoader.doRequest(f);
		//String content = prepareContentForParse(resp.result);
		String content = resp.result;
		
		
		Document doc = Jsoup.parse(content);
		int totalAds = getTotalAds(doc);
		
		
		Elements elements = doc.select("table.sales-list-table");
				
		WareList wares = new WareList();
		wares.setTotalCount(totalAds);
		
		for(int  i =0 ; i < elements.size(); i++){
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
	
	private int getTotalAds(Document doc) {
		Elements els = doc.select("li.tabs-v4-i_active > sup");
		return Integer.valueOf(els.text());
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
		String dateStr = ware.getParam(EParam.DATE_STR);
		ware.addParam(EParam.DATE, String.valueOf(dateConverter.convert(dateStr)));
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
		MarkAndModel mam = markAndModelConverter.convert(ware);
		ware.addParam(EParam.MARK, mam.mark);
		ware.addParam(EParam.MODEL, mam.model);
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
		String colorAndBodyType = getBodyTypeAndColor(element);
		//String markAndModel = getMarkAndModel(element);
		String misc = getCommonDesc(element);
		
		String fullDesc = colorAndBodyType + " " + misc;
		ware.addParam(EParam.DESC_SHORT, fullDesc);
		
		AutoDesc ad = autoDescConverter.convert(colorAndBodyType,  misc);
				
		ware.addParam(EParam.BODY_TYPE, ad.getBodyType());
		ware.addParam(EParam.COLOR, ad.getColor());
		ware.addParam(EParam.DRIVING_GEAR, ad.getDrivingGear());
		ware.addParam(EParam.ENGINE_VOLUME, String.valueOf(ad.getEngineVolume()));
		ware.addParam(EParam.FUEL, String.valueOf(ad.getFuel()));
		ware.addParam(EParam.GEAR_BOX_TYPE, String.valueOf(ad.getGearBoxType()));
		ware.addParam(EParam.HORSES, String.valueOf(ad.getHorses()));
		ware.addParam(EParam.POWER, String.valueOf(ad.getPower()));
				
	}
	
	private String getCommonDesc(Element element){
		return findValue(element, "td", "sales-list-cell sales-list-cell_mark_id");
	}
	
	/*
	private String getMarkAndModel(Element element){
		Element el = findElement(element, "td", "sales-list-cell sales-list-cell_mark_id");
		return el.child(0).text();
	}
	*/
	
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
