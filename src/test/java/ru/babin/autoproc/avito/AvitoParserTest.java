package ru.babin.autoproc.avito;

import java.util.List;

import org.junit.Test;

import ru.babin.autoproc.api.filter.Filter;
import ru.babin.autoproc.api.model.ECategory;
import ru.babin.autoproc.api.model.ERegion;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.parser.avito.AvitoParser;

public class AvitoParserTest {
	
	AvitoParser parser = new AvitoParser();
	
	@Test
	public void test_loadData(){
		Filter f = new Filter();
		f.setRegion(ERegion.MOSCOW);
		f.setCategory(ECategory.AUTO);
		
		List <Ware> wares = parser.parse(f);
		
		for(Ware w : wares){
			System.out.println(w);
		}
	}
	
}
