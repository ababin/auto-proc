package ru.babin.autoproc.impl.auto_ru;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import ru.babin.autoproc.impl.autoru.parser.AutoruParamsLoader;

public class AutoruParamsLoaderTest {
	
	
	@Test
	public void test_loadBrands(){
		AutoruParamsLoader loader = new AutoruParamsLoader();
		Map <String,String> res =  loader.getBrands();
		Assert.assertEquals(res.size() , 149);
	}
	
}
