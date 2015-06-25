package ru.babin.autoproc.impl.autoru;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import ru.babin.autoproc.api.model.AutoDesc;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruAutoDescConverter;

public class AutoruAutoDescConverterTest {
	
	AutoruAutoDescConverter converter = new AutoruAutoDescConverter();
	
	@Test
	public void test1(){
		
		String colorAndBodyType = "Черный внедорожник 5 дв.";
		String misc = "2.5hyb CVT (155 л.с.) 4WD гибрид, полный";
		
		AutoDesc ad = converter.convert(colorAndBodyType, misc);
		
		assertEquals(ad.getColor(), "черный");
		assertEquals(ad.getBodyType(), "внедорожник 5 дв.");
		assertEquals(ad.getDrivingGear(), "полный");
		assertEquals(ad.getEngineVolume(), 25);
		assertEquals(ad.getFuel(), "гибрид");
		assertEquals(ad.getGearBoxType(), "CVT");
		assertEquals(ad.getHorses(), 155);
	}
	
	@Test
	public void test2(){
		
		String colorAndBodyType = "Черный внедорожник 5 дв.";
		String misc = "MT бензин, передний";
		
		AutoDesc ad = converter.convert(colorAndBodyType, misc);
		
		assertEquals(ad.getColor(), "черный");
		assertEquals(ad.getBodyType(), "внедорожник 5 дв.");
		assertEquals(ad.getDrivingGear(), "передний");
		assertEquals(ad.getEngineVolume(), 0);
		assertEquals(ad.getFuel(), "бензин");
		assertEquals(ad.getGearBoxType(), "MT");
		assertEquals(ad.getHorses(), 0);
	}
			
	@Test
	public void test3(){
		
		String colorAndBodyType = "Черный внедорожник 5 дв.";
		String misc = "Electro AT (516 кВт) 4WD электро, полный";
		
		AutoDesc ad = converter.convert(colorAndBodyType, misc);
		
		assertEquals(ad.getColor(), "черный");
		assertEquals(ad.getBodyType(), "внедорожник 5 дв.");
		assertEquals(ad.getDrivingGear(), "полный");
		assertEquals(ad.getEngineVolume(), 0);
		assertEquals(ad.getFuel(), "электро");
		assertEquals(ad.getGearBoxType(), "AT");
		assertEquals(ad.getHorses(), 0);
		assertEquals(ad.getPower(), 516);
	}
	
}
