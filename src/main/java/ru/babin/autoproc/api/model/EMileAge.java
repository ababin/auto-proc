package ru.babin.autoproc.api.model;

public enum EMileAge {
	
	MA_0("0 км", 15483),
	MA_5("5 тыс км", 15486),
	MA_10("10 тыс км", 15487),
	MA_15("15 тыс км", 15490),
	MA_20("20 тыс км", 15492),
	MA_25("25 тыс км", 15494),
	MA_30("30 тыс км", 15496),
	MA_35("35 тыс км", 15498),
	MA_40("40 тыс км", 15500),
	MA_45("45 тыс км", 15502),
	MA_50("50 тыс км", 15505),
	MA_60("60 тыс км", 15509),
	MA_70("70 тыс км", 15512),
	MA_80("80 тыс км", 15516),
	MA_90("90 тыс км", 15520),
	MA_100("100 тыс км", 15524),
	MA_110("110 тыс км",15527 ),
	MA_120("120 тыс км", 15528),
	MA_130("130 тыс км", 15531),
	MA_140("140 тыс км", 15533),
	MA_150("150 тыс км", 15535),
	MA_160("160 тыс км", 15536),
	MA_170("170 тыс км", 15539),
	MA_180("180 тыс км", 15540),
	MA_190("190 тыс км", 15542),
	MA_200("200 тыс км", 15544),
	
	MA_250("250 тыс км", 15554),
	MA_300("300 тыс км", 15560),
	MA_350("350 тыс км", 15565),
	MA_400("400 тыс км", 15570),
	MA_450("450 тыс км", 15575),
	MA_500("500+ тыс км", 15581),
	;
	
	
	private String readableName;
	private int code;
	
	private EMileAge(String n, int id){
		readableName = n; 
		this.code = id;
	}

	public String getReadableName() {
		return readableName;
	}
	
	public int getCode() {
		return code;
	}
	
	public int getMiles(){
		String [] ar = this.name().split("_");
		int miles = Integer.valueOf(ar[1]);
		return miles * 1000;
	}
	
	
}
