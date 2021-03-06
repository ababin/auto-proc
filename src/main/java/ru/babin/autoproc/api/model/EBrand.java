package ru.babin.autoproc.api.model;

public enum EBrand {
	
	_ANY("Любая"),
	
	AC("AC"),
	ACURA("Acura"),
	ALFA_ROMEO("Alfa Romeo"),
	ALPINA("Alpina"),
	ARO("Aro"),
	ASIA("Asia"),
	ASTON_MARTIN("Aston Martin"),
	AUDI("Audi"),
	BAW("BAW"),
	BENTLEY("Bentley"),
	BMW("BMW"),
	BRILLIANCE("Brilliance"),
	BUFORI("Bufori"),
	BUGATTI("Bugatti"),
	BUICK("Buick"),
	BYD("BYD"),
	CADILLAC("Cadillac"),
	CATERHAM("Caterham"),
	CHANGAN("Changan"),
	CHANGFENG("ChangFeng"),
	CHERY("Chery"),
	CHEVROLET("Chevrolet"),
	CHRYSLER("Chrysler"),
	CITROEN("Citroen"),
	DACIA("Dacia"),
	DADI("Dadi"),
	DAEWOO("Daewoo"),
	DAIHATSU("Daihatsu"),
	DAIMLER("Daimler"),
	DATSUN("Datsun"),
	DERWAYS("Derways"),
	DODGE("Dodge"),
	DONG_FENG("Dong Feng"),
	DONINVEST("Doninvest"),
	EAGLE("Eagle"),
	ECOMOTORS("Ecomotors"),
	FAW("FAW"),
	FERRARI("Ferrari"),
	FIAT("FIAT"),
	FORD("Ford"),
	GEELY("Geely"),
	GEO("Geo"),
	GMC("GMC"),
	GREAT_WALL("Great Wall"),
	HAFEI("Hafei"),
	HAIMA("Haima"),
	HAVAL("Haval"),
	HAWTAI("Hawtai"),
	HONDA("Honda"),
	HUANGHAI("Huanghai"),
	HUMMER("Hummer"),
	HURTAN("Hurtan"),
	HYUNDAI("Hyundai"),
	INFINITI("Infiniti"),
	IRAN_KHODRO("Iran Khodro"),
	ISUZU("Isuzu"),
	IVECO("Iveco"),
	JAC("JAC"),
	JAGUAR("Jaguar"),
	JEEP("Jeep"),
	JINBEI("Jinbei"),
	JMC("JMC"),
	KIA("KIA"),
	LAMBORGHINI("Lamborghini"),
	LANCIA("Lancia"),
	LAND_ROVER("Land Rover"),
	LANDWIND("Landwind"),
	LDV("LDV"),
	LEXUS("Lexus"),
	LIFAN("LIFAN"),
	LINCOLN("Lincoln"),
	LOTUS("Lotus"),
	LUXGEN("Luxgen"),
	MAHINDRA("Mahindra"),
	MARUSSIA("Marussia"),
	MASERATI("Maserati"),
	MAYBACH("Maybach"),
	MAZDA("Mazda"),
	MERCEDES_BENZ("Mercedes-Benz"),
	MERCURY("Mercury"),
	METROCAB("Metrocab"),
	MG("MG"),
	MINI("MINI"),
	MITSUBISHI("Mitsubishi"),
	MITSUOKA("Mitsuoka"),
	MORGAN("Morgan"),
	MORRIS("Morris"),
	NISSAN("Nissan"),
	NOBLE("Noble"),
	OLDSMOBILE("Oldsmobile"),
	OPEL("Opel"),
	PAGANI("Pagani"),
	PEUGEOT("Peugeot"),
	PLYMOUTH("Plymouth"),
	PONTIAC("Pontiac"),
	PORSCHE("Porsche"),
	PROTON("Proton"),
	RENAULT("Renault"),
	ROLLS_ROYCE("Rolls-Royce"),
	RONART("Ronart"),
	ROVER("Rover"),
	SAAB("Saab"),
	SALEEN("Saleen"),
	SATURN("Saturn"),
	SCION("Scion"),
	SEAT("SEAT"),
	SHUANGHUAN("Shuanghuan"),
	SKODA("Skoda"),
	SMA("SMA"),
	SMART("Smart"),
	SPYKER("Spyker"),
	SSANGYONG("SsangYong"),
	SUBARU("Subaru"),
	SUZUKI("Suzuki"),
	TALBOT("Talbot"),
	TATA("Tata"),
	TESLA("Tesla"),
	TIANMA("Tianma"),
	TIANYE("Tianye"),
	TOYOTA("Toyota"),
	TRABANT("Trabant"),
	VOLKSWAGEN("Volkswagen"),
	VOLVO("Volvo"),
	VORTEX("Vortex"),
	WARTBURG("Wartburg"),
	WIESMANN("Wiesmann"),
	XIN_KAI("Xin Kai"),
	ZX("ZX"),
	VAZ_LADA("ВАЗ (LADA)"),
	VIS("ВИС"),
	GAZ("ГАЗ"),
	ZAZ("ЗАЗ"),
	ZIL("ЗИЛ"),
	IZH("ИЖ"),
	LUAZ("ЛуАЗ"),
	MOSKVICH("Москвич"),
	RAF("РАФ"),
	TAGAZ("ТагАЗ"),
	UAZ("УАЗ"),
	DRUGAYA("Другая"),
	
	
	_FOREIGN("Иномарки"),
	_NATIVE("Отечественные"),
	_EUROPE("Европейские"),
	_JAPAN("Японские"),
	_KOREA("Корейские"),
	_USA("Американские"),
	_CHINA("Китайские"),
	_EXCLUDE_CHINA("Кроме китайских"),
		
	
	;
	
	private String readableName;
	
	private EBrand(String str){
		readableName = str;
	}
	
	public String getReadableName(){
		return readableName;
	}
	
}
