package ru.babin.autoproc.api.model;

public class Brand {
	
	private String name;
	
	private String formattedName;
	
	private String code;

	public Brand(String name, String formattedName, String code){
		this.name = name;
		this.formattedName = formattedName;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormattedName() {
		return formattedName;
	}

	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
