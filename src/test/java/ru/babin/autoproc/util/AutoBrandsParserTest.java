package ru.babin.autoproc.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.junit.Test;

import ru.babin.autoproc.util.avito.AutoBrandsParser;

public class AutoBrandsParserTest {

	AutoBrandsParser parser = new AutoBrandsParser();
	
	@Test
	public void test(){
		LinkedHashMap <String,String> map = parser.getAutoCats();
		Iterator<Entry<String,String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			System.out.println( entry.getKey().toUpperCase() + "(\"" + entry.getValue() + "\"),");
		}
	}
	
	
}
