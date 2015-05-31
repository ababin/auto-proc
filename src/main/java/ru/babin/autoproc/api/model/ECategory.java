package ru.babin.autoproc.api.model;

public enum ECategory {
	
	AUTO("Автомобили"),
	
	;
	
	private String readableName;
	
	private ECategory(String str){
		readableName = str;
	}
	
	public String getReadableName(){
		return readableName;
	}
	
}
