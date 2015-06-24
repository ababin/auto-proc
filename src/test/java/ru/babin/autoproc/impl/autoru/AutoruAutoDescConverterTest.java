package ru.babin.autoproc.impl.autoru;

import org.junit.Test;

import ru.babin.autoproc.api.model.AutoDesc;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruAutoDescConverter;

public class AutoruAutoDescConverterTest {
	
	AutoruAutoDescConverter converter = new AutoruAutoDescConverter();
	
	@Test
	public void test1(){
		Ware w = new Ware();
		w.addParam(EParam.DESC_SHORT, "Серый седан MT бензин, передний");
		
		AutoDesc ad = converter.convert(w);
	}
	
	
}
