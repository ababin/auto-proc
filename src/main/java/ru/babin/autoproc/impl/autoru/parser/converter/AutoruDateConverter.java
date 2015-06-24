package ru.babin.autoproc.impl.autoru.parser.converter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AutoruDateConverter {
	
	private static final String TODAY = "сегодня";
	private static final String YESTERDAY = "вчера";
	
	private static final String [] monthes = new String[] {"янв","фев","мар","апр","мая","июн","июл","авг","сен","окт","ноя","дек"}; 
	
	
	public long convert(String dateStr){
		if(TODAY.equalsIgnoreCase(dateStr)){
			Calendar c = GregorianCalendar.getInstance();
			setBeginOfDate(c);
			return c.getTimeInMillis();
		}
		if(YESTERDAY.equalsIgnoreCase(dateStr)){
			Calendar c = GregorianCalendar.getInstance();
			setBeginOfDate(c);
			c.add(Calendar.DAY_OF_MONTH, -1);
			return c.getTimeInMillis();
		}
		
		String [] ar = dateStr.split(" ");
		int day = Integer.valueOf(ar[0]);
		int month = -1;
		for(int i = 0; i < monthes.length; i++){
			if(monthes[i].equalsIgnoreCase(ar[1])){
				month = i;
				break;
			}
		}
		
		Calendar c = GregorianCalendar.getInstance();
		if(c.get(Calendar.MONTH) < month){
			c.add(Calendar.YEAR, -1);
		}
		c.set(Calendar.DAY_OF_MONTH , day);
		c.set(Calendar.MONTH, month);
		
		setBeginOfDate(c);
		
		return c.getTimeInMillis();
		
	}
	
	private void setBeginOfDate(Calendar c){
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
	}
	
}
