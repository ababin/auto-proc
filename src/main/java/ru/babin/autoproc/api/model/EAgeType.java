package ru.babin.autoproc.api.model;

public enum EAgeType {
	
	NEW("Новые"),
	WITH_MILEAGE("С пробегом"),
	ALL("Все"),
			
	;
	
	private String readableName;
	
	private EAgeType(String name){
		readableName = name;
	}
	
	public String getReadableName(){
		return readableName;
	}
	
	
}
