package ru.babin.autoproc.impl.avito;

import java.util.List;

import org.junit.Test;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EGearBoxType;
import ru.babin.autoproc.api.model.EModel;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.impl.avito.parser.AvitoWareLoader;

public class AvitoWareLoaderTest {
	
	AvitoWareLoader wareLoader = new AvitoWareLoader();
	
	@Test
	public void test_loadData(){
		
		AutoFilter f = new AutoFilter();
		//f.setRegion(ERegion.RUSSIA);
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		f.setBrand(EBrand.HYUNDAI);
		f.setModel(EModel.HYUNDAI___SOLARIS);
		f.addAutoBodyType(EAutoBodyType.SEDAN);
		f.setPersonality(EPersonality.PRIVATE);
		f.setPrice(350000, 440000);
		f.addGearBoxType(EGearBoxType.AUTOMAT);
		f.setMileage(10000, 40000);
		f.setYearFrom(2010);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		
		List <Ware> wares = wareLoader.load(f);
		
		for(Ware w : wares){
			System.out.println(
					w.getParam(EParam.NAME) + "   " + 
					w.getParam(EParam.DESC_SHORT) + "   " + 
					w.getParam(EParam.PRICE_STR) + "    " + 
					w.getParam(EParam.ADS_URL)); 
		}
	}
	
}
