package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.AutoBodyConverter;
import ru.babin.autoproc.api.model.EAutoBodyType;

public class AutoruAutoBodyConverter implements AutoBodyConverter{

	@Override
	public String convert(EAutoBodyType body) {
		switch(body){
		case SEDAN				:	return "g_sedan";
		case VNEDOROZHNIK		:	return "g_offroad";
		case VNEDOROZHNIK_3		:	return "1320";
		case VNEDOROZHNIK_5		:	return "1358";
		case HETCHBEK			:	return "g_hatchback";
		case HETCHBEK_3			:	return "1316";
		case HETCHBEK_5			:	return "121";
		case HETCHBEK_LIFTBACK	:	return "1314";
		case UNIVERSAL			:	return "g_wagon";
		case MINIVEN			:	return "g_minivan";
		case PIKAP				:	return "g_pickup";
		case LIMUZIN			:	return "g_limousine";
		case KUPE				:	return "g_coupe";
		case FURGON				:	return "g_furgon";
		case KABRIOLET			:	return "g_cabrio";
		
		case KROSSOVER			:	return "g_offroad";
		 
		}
		
		throw new RuntimeException("Value " + body + " is not available for AutoruAutoBodyConverter !");
	}
	
	
	
}
