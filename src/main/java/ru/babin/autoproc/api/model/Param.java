package ru.babin.autoproc.api.model;

public class Param {
	
	public final EParam param;
	public final String value;
	
	public Param(EParam param, String value){
		this.param = param;
		this.value = value;
	}
	
	public String toString(){
		return "{"+param.name() +"=" + value + "}";
	}
	
}
