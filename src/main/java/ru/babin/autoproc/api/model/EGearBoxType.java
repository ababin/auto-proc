package ru.babin.autoproc.api.model;

public enum EGearBoxType {
	
	MECHANIC("Механика"),
	
	AUTOMAT("Автомат"),
	
	AUTOMAT_AUTOMATIC("Автоматическая"),
	
	AUTOMAT_ROBOT("Роботизированная"),
	
	AUTOMAT_VARIATOR("Вариатор"),
	;
	
	
	private String readableName;
		
	private EGearBoxType(String n){
		readableName = n; 
	}

	public String getReadableName() {
		return readableName;
	}
	
}
