package ru.babin.autoproc.api.model;

import java.util.LinkedList;
import java.util.List;

public class Ware {
	
	List <Param> params = new LinkedList <>();
	
	public String getParam(EParam paramIn){
		for(Param param : params){
			if(param.param == paramIn){
				return param.value;
			}
		}
		return null;
	}
	
}
