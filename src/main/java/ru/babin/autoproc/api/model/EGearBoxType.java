package ru.babin.autoproc.api.model;

public enum EGearBoxType {
	
	MECHANIC("Механика",861),
	
	AUTOMAT("Автомат",860),
	
	ROBOT("Робот",14754),
	
	VARIATOR("Вариатор",14753),
	;
	
	
	private String readableName;
	private int id;
	
	private EGearBoxType(String n, int id){
		readableName = n; 
		this.id = id;
	}

	public String getReadableName() {
		return readableName;
	}
	
	public int getId() {
		return id;
	}
	
	
}
