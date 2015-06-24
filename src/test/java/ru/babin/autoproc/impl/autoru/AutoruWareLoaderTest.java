package ru.babin.autoproc.impl.autoru;

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
		f.setNeedPhone(true);
		f.setPage(1);
								
		List <Ware> wares = wareLoader.load(f);
		
		long dEnd = System.currentTimeMillis();
		
		for(Ware w : wares){
			System.out.println(
					w.getParam(EParam.DATE_STR) + "     " + 
					w.getParam(EParam.NAME) + "   " + 
					w.getParam(EParam.DESC_SHORT) + "   " + 
					w.getParam(EParam.PRICE_STR) + "    " + 
					w.getParam(EParam.ADS_URL) + "    " +
					w.getParam(EParam.PHONE));
		}
		long ms = dEnd - dBegin;
		System.out.println("---------------------------------------------------------------");
		System.out.println("Total wares: " + wares.size() + " during " + ms);
		long speed = ms / wares.size();
		System.out.println("Speed: " + speed + " ms/record");
	}
	
}
