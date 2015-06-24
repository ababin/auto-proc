package ru.babin.autoproc.impl.autoru;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.Assert;
import ru.babin.autoproc.impl.autoru.parser.converter.AutoruDateConverter;

public class AutoruDateConverterTest {
	
	AutoruDateConverter converter = new AutoruDateConverter();
	
	@Test
	public void test_today(){
		long l = converter.convert("сегодня");
		Calendar c = GregorianCalendar.getInstance();
		setBeginOfDate(c);
		Assert.assertEquals(l, c.getTimeInMillis());
	}
	
	@Test
	public void test_yesterday(){
		long l = converter.convert("вчера");
		Calendar c = GregorianCalendar.getInstance();
		setBeginOfDate(c);
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		Assert.assertEquals(l, c.getTimeInMillis());
	}
	
	@Test
	public void test_someDateBefore(){
		long l = converter.convert("10 июн");
		
		Calendar c = GregorianCalendar.getInstance();
		setBeginOfDate(c);
		c.set(Calendar.MONTH, Calendar.JUNE);
		c.set(Calendar.DAY_OF_MONTH, 10);
				
		Assert.assertEquals(l, c.getTimeInMillis());
	}
	
		
	
	
	private void setBeginOfDate(Calendar c){
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}
	
	
}
