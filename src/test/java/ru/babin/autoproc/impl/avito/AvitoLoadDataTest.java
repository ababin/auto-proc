package ru.babin.autoproc.impl.avito;

import org.junit.Test;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.impl.avito.parser.AvitoHttpLoader;

public class AvitoLoadDataTest {
	
	AvitoHttpLoader loader = new AvitoHttpLoader();
	
	@Test
	public void test_loadData(){
		AutoFilter f = new AutoFilter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		
		Response resp = loader.doRequest(f);
		
		System.out.println(resp.result);
	}
	
	
}
