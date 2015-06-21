package ru.babin.autoproc.api.model;

public enum EAgeType {
	
	ALL("Все"),
	NEW("Новые"),
	WITH_MILEAGE("С пробегом"),
	
			
	;
	
	private String readableName;
	
	private EAgeType(String name){
		readableName = name;
	}
	
	public String getReadableName(){
		return readableName;
	}
	
	
}
