package ru.babin.autoproc.api.model;

public enum EPersonality {
	
	ALL(0),
	PRIVATE(1),
	AUTODEALER(2);
	
	private int code;
	
	private EPersonality(int c){
		code = c;
	}
	
	public boolean isAll(){
		return this == ALL;
	}
	
	public int getCode(){
		return code;
	}
	
}
