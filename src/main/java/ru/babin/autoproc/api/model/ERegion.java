package ru.babin.autoproc.api.model;

public enum ERegion {
	
	RUSSIA("РФ"),
	
	MOSCOW("Москва"),
	
	;
	
	private String readableName;
	
	private ERegion(String n){
		readableName = n;
	}
	
	public String getReadableName(){
		return readableName;
	}
}
