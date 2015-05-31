package ru.babin.autoproc.parser.avito.converter;

import ru.babin.autoproc.api.model.EModel;

public class AvitoModelConverter {

	
	public String convert(EModel model){
		switch(model){
		case CHEVROLET___LUV_D_MAX		:	return "luv_d-max";
		
		case FORD___F_150				:	return "f150";
		case FORD___F_250				:	return "f250";
		case FORD___F_350				:	return "f350";
		case FORD___GRAND_C_MAX			:	return "grand_c-max";
		
		case HYUNDAI___H_1_GRAND_STAREX	:	return "h-1_grand_starex";
		
		case MERCEDES_BENZ___A_KLASS_AMG	:	return "a-klass_amg";
		case MERCEDES_BENZ___CLA_KLASS_AMG	:	return "cla-klass_amg";
		case MERCEDES_BENZ___CLK_KLASS_AMG	:	return "clk-klass_amg";
		case MERCEDES_BENZ___CLS_KLASS_AMG	:	return "cls-klass_amg";
		case MERCEDES_BENZ___CL_KLASS_AMG	:	return "cl-klass_amg";
		case MERCEDES_BENZ___C_KLASS_AMG	:	return "c-klass_amg";
		case MERCEDES_BENZ___E_KLASS_AMG	:	return "e-klass_amg";
		case MERCEDES_BENZ___GLA_KLASS_AMG	:	return "gla-klass_amg";
		case MERCEDES_BENZ___GLE_KLASS_AMG	:	return "gle-klass_amg";
		case MERCEDES_BENZ___GL_KLASS_AMG	:	return "gl-klass_amg";
		case MERCEDES_BENZ___G_KLASS_AMG	:	return "g-klass_amg";
		case MERCEDES_BENZ___MAYBACH_S_KLASS	:	return "maybach_s-klass";
		case MERCEDES_BENZ___ML_KLASS_AMG	:	return "ml-klass_amg";
		case MERCEDES_BENZ___R_KLASS_AMG	:	return "r-klass_amg";
		case MERCEDES_BENZ___SLK_KLASS_AMG	:	return "slk-klass_amg";
		case MERCEDES_BENZ___SLS_KLASS_AMG	:	return "sls-klass_amg";
		case MERCEDES_BENZ___SL_KLASS_AMG	:	return "sl-klass_amg";
		case MERCEDES_BENZ___S_KLASS_AMG	:	return "s-klass_amg";
		
		case XIN_KAI___SR_V_X3				:	return "sr-v_x3";
		
		case CHRYSLER___TOWN__COUNTRY	:	return "town__country";
		
		default						:	String [] ar = model.name().split("___");
										if(model.name().contains("-")){
											return ar[1].toLowerCase().replace("_", "-");
										}else{
											return ar[1].toLowerCase();
										}
										
		}
		
		
	}
	
	
}
