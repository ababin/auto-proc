package ru.babin.autoproc.api.model;

public enum EGearBoxType {
	
	MECHANIC("Механическая", "MT"),
	
	AUTOMAT("Автомат", "AT"),
	
	AUTOMAT_AUTOMATIC("Автоматическая" , "AT"),
	
	AUTOMAT_ROBOT("Роботизированная", "AT"),
	
	AUTOMAT_VARIATOR("Вариатор", "CVT"),
	;
	
	
	private String readableName;
	
	private String dbVal;
		
	private EGearBoxType(String n, String val){
		readableName = n;
		dbVal = val;
	}

	public String getReadableName() {
		return readableName;
	}

	public String getDbVal() {
		return dbVal;
	}
		
}
