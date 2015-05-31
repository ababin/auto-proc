package ru.babin.autoproc.api.model;

public enum EAutoBodyType {
	
	SEDAN("Седан", 869),
	HETCHBEK("Хетчбэк" , 872),
	UNIVERSAL("Универсал", 870),
	VNEDOROZHNIK("Внедорожник", 4804),
	KABRIOLET("Кабриолет", 865),
	KROSSOVER("Кроссовер", 4805),
	KUPE("Купе",866),
	LIMUZIN("Лимузин",867),
	MINIVEN("Минивэн",4806),
	PIKAP("Пикап",868),
	FURGON("Фургон", 871),
	MIKROAVTOBUS("Микроавтобус",11695),
	
	;
	
	private String readableName;
	private int id;
	
	private EAutoBodyType(String n, int id){
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
