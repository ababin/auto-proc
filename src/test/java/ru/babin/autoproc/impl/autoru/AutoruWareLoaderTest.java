package ru.babin.autoproc.impl.autoru;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EGearBoxType;
import ru.babin.autoproc.api.model.EMileAge;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.EYear;
import ru.babin.autoproc.api.model.Ware;
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
		f.setYear(EYear.YEAR_2010, EYear.YEAR_2015);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.addAutoBodyType(EAutoBodyType.SEDAN);
		f.setBrand(EBrand._KOREA);
		f.addGearBoxType(EGearBoxType.AUTOMAT);
		f.setMileAgeTo(EMileAge.MA_70);
				
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
		f.setYear(EYear.YEAR_2015, EYear.YEAR_2015);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		//f.setNeedPhone(true);
		
		List <Ware> allWares = new LinkedList<>();
		
		for(int page = 1; page < 141 ; page++){
			System.out.println("PAGE " + page + " : *********************************************************************************");
			f.setPage(page);
			
			List <Ware> wares = wareLoader.load(f);
			if(wares.isEmpty()){
				break;
			}
			allWares.addAll(wares);
			
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
		System.out.println("Total wares: " + allWares.size() + " during " + ms);
		long speed = ms / allWares.size();
		System.out.println("Speed: " + speed + " ms/record");
	}
	
	private String getFormatDate(long date){
		return df.format(new Date(date));
	}
	
}
