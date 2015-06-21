package ru.babin.autoproc.api.model;

public enum EYear {

	YEAR_1960(1960, 771),
	YEAR_1970(1970, 782),
	YEAR_1980(1980, 873),
	YEAR_1985(1985, 878),
	YEAR_1990(1990, 883),
	YEAR_1991(1991, 884),
	YEAR_1992(1992, 885),
	YEAR_1993(1993, 886),
	YEAR_1994(1994, 887),
	YEAR_1995(1995, 888),
	YEAR_1996(1996, 889),
	YEAR_1997(1997, 890),
	YEAR_1998(1998, 891),
	YEAR_1999(1999, 892),
	YEAR_2000(2000, 893),
	YEAR_2001(2001, 894),
	YEAR_2002(2002, 895),
	YEAR_2003(2003, 896),
	YEAR_2004(2004, 897),
	YEAR_2005(2005, 898),
	YEAR_2006(2006, 899),
	YEAR_2007(2007, 900),
	YEAR_2008(2008, 901),
	YEAR_2009(2009, 902),
	YEAR_2010(2010, 2844),
	YEAR_2011(2011, 2845),
	YEAR_2012(2012, 6045),
	YEAR_2013(2013, 8581),
	YEAR_2014(2014, 11017),
	YEAR_2015(2015, 13978),
	
	;
	
	private int year;
	private int code;
	
	private EYear(int year, int code){
		this.year = year;
		this.code = code;
	}
	
	public int getYear(){
		return year;
	}
	
	public int getCode(){
		return code;
	}
	
	public static EYear fromYear(Integer year){
		if(year == null){
			return EYear.YEAR_1960;
		}
		for(EYear y : EYear.values()){
			if(y.year >= year){
				return y;
			}
		}
		return EYear.YEAR_2015;
	}
}
