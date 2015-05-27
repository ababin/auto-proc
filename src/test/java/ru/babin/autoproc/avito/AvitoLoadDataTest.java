package ru.babin.autoproc.avito;

import org.junit.Test;

import ru.babin.autoproc.api.filter.Filter;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.http.Response;
import ru.babin.autoproc.parser.avito.AvitoLoader;

public class AvitoLoadDataTest {
	
	AvitoLoader loader = new AvitoLoader();
	
	//@Test
	public void test_loadData(){
		Filter f = new Filter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		
		Response resp = loader.loadData(f);
		
		System.out.println(resp.result);
		
	}
	
	
}
