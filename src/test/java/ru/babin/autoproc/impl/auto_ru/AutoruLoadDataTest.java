package ru.babin.autoproc.impl.auto_ru;

import org.junit.Test;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EAutoBodyType;
import ru.babin.autoproc.api.model.EBrand;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.EYear;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.autoru.parser.AutoruHttpLoader;

public class AutoruLoadDataTest {
	
	AutoruHttpLoader loader = new AutoruHttpLoader();
	
	@Test
	public void test_loadData(){
		AutoFilter f = new AutoFilter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		f.setPrice(350000, 440000);
		f.setPersonality(EPersonality.PRIVATE);
		f.setYear(EYear.YEAR_2010, EYear.YEAR_2015);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.addAutoBodyType(EAutoBodyType.SEDAN);
		f.setBrand(EBrand._KOREA);
		
		Response resp = loader.doRequest(f);
		
		System.out.println(resp.result);
		
		//System.out.println(resp.result);
	}
	
	
}
