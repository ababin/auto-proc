package ru.babin.autoproc.impl.autoru;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EGearBoxType;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.api.model.WareList;
import ru.babin.autoproc.impl.autoru.parser.AutoruWareLoader;

public class AutoruWareLoaderTest {
	
	AutoruWareLoader wareLoader = new AutoruWareLoader();
	
	DateFormat df = new SimpleDateFormat("dd.MM");
	
	//@Test
	public void test_loadData(){
		
		AutoFilter f = new AutoFilter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		f.setPrice(350000, 430000);
		f.setPersonality(EPersonality.PRIVATE);
		f.setYear(2010, 2015);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.addAutoBodyType(EAutoBodyType.SEDAN);
		f.setBrand(EBrand._KOREA);
		f.addGearBoxType(EGearBoxType.AUTOMAT);
		f.setMileAgeTo(70000);
				
		List <Ware> wares = wareLoader.load(f);
		
		for(Ware w : wares){
			System.out.println(
					w.getParam(EParam.DATE_STR) + "     " + 
					w.getParam(EParam.NAME) + "   " + 
					w.getParam(EParam.DESC_SHORT) + "   " + 
					w.getParam(EParam.PRICE_STR) + "    " + 
					w.getParam(EParam.ADS_URL)); 
		}
	}
	
	@Test
	public void test_loadFullData(){
		
		AutoFilter f = new AutoFilter();
		
		long dBegin = System.currentTimeMillis();
		
		f.setPersonality(EPersonality.PRIVATE);
		f.setYear(2012, 2012);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		//f.setNeedPhone(true);
		
		WareList allWares = new WareList();
		
		for(int page = 1; page < 150 ; page++){
			System.out.println("PAGE " + page + " : *********************************************************************************");
			f.setPage(page);
			
			WareList wares = wareLoader.load(f);
			if(wares.isEmpty()){
				break;
			}
			allWares.addAll(wares);
			allWares.setTotalCount(wares.getTotalCount());
			
		}
			
		
		
		long dEnd = System.currentTimeMillis();
		
		for(Ware w : allWares){
			System.out.println(
					getFormatDate(Long.valueOf(w.getParam(EParam.DATE))) + "    " +  
					w.getParam(EParam.MARK) + " *** " +
					w.getParam(EParam.MODEL) + "                             " +
					
					w.getParam(EParam.COLOR) + " & " + 
					w.getParam(EParam.BODY_TYPE) + " & " +
					w.getParam(EParam.DRIVING_GEAR) + " & " +
					w.getParam(EParam.ENGINE_VOLUME) + " & " +
					w.getParam(EParam.FUEL) + " & " +
					w.getParam(EParam.GEAR_BOX_TYPE) + " & " +
					w.getParam(EParam.HORSES) + "           " +
					
					

					/* w.getParam(EParam.DESC_SHORT) + "   " + */ 
					w.getParam(EParam.PRICE_STR) + "    " + 
					w.getParam(EParam.ADS_URL) + "    "  
					//w.getParam(EParam.PHONE));
					);
		}
		long ms = dEnd - dBegin;
		System.out.println("---------------------------------------------------------------");
		System.out.println("Wares: " + allWares.size() + " during " + ms + "        TOTAL: " + allWares.getTotalCount());
		long speed = ms / allWares.size();
		System.out.println("Speed: " + speed + " ms/record");
	}
	
	
	@Test
	public void test_parseColorAndBodyType(){
		
		AutoFilter f = new AutoFilter();
						
		f.setPersonality(EPersonality.PRIVATE);
		f.setYear(1991, 1991);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.setPrice(195000, 195000);
						
		WareList wares = wareLoader.load(f);
		
	}
	
	@Test
	public void test_parsePravyRul(){
		
		AutoFilter f = new AutoFilter();
						
		f.setPersonality(EPersonality.PRIVATE);
		f.setYear(1991, 1991);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.setPrice(115000,  115000);
						
		WareList wares = wareLoader.load(f);
		
	}
	
	@Test
	public void test_parseBitiy(){
		
		AutoFilter f = new AutoFilter();
						
		f.setPersonality(EPersonality.PRIVATE);
		f.setYear(1991, 1991);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.setPrice(210000,  210000);
						
		WareList wares = wareLoader.load(f);
		
	}
	
	
	private String getFormatDate(long date){
		return df.format(new Date(date));
	}
	
}
