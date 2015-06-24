package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.model.AutoDesc;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.Ware;

public class AutoruAutoDescConverter {
	
	public AutoDesc convert(Ware w){
		
		// Серебряный универсал 5 дв. 1.6 MT (84 л.с.) бензин, передний
		System.out.println(w.getParam(EParam.DESC_SHORT));
		String [] ar = w.getParam(EParam.DESC_SHORT).split(" ");
		
		// 1.
		String color = ar[0];
		
		// find index of gearBoxType
		int gearBoxTypeIndex = -1;
		for(int i = 1; i < ar.length ; i++){
			if(ar[i].equals("AT") || ar[i].equals("MT") || ar[i].equals("CVT")){
				gearBoxTypeIndex = i;
				break;
			}
		}
		
		// 2.
		String gearBoxType = ar[gearBoxTypeIndex];
		
		// 3. 
		int gearVolume = 0;
		if(ar[gearBoxTypeIndex-1].contains(".")){
			gearVolume = getAsInt(ar[gearBoxTypeIndex-1]);
		}
				
		// 4.
		String bodyType = "";
		for(int i = 1; i < gearBoxTypeIndex - 1; i++){
			bodyType += " " + ar[i];
		}
		bodyType = bodyType.trim();
		
		// 5. 
		String horses = ar[gearBoxTypeIndex+1].replace("(", "");
		
		// 6. fuel
		String fuel = ar[ar.length-2].replace(",", "");
		
		// 7. gearDrive
		String gearDrive = ar[ar.length-1];
		
		AutoDesc ad = new AutoDesc();
		ad.setBodyType(bodyType);
		ad.setColor(color);
		ad.setDrivingGear(gearDrive);
		ad.setEngineVolume(gearVolume);
		ad.setFuel(fuel);
		ad.setGearBoxType(gearBoxType);
		ad.setHorses(Integer.valueOf(horses));
		
		return ad;
		
	}
	
	private int getAsInt(String str){
		StringBuilder sb = new StringBuilder();
		for(char c : str.toCharArray()){
			if("0123456789".indexOf(c) >= 0){
				sb.append(c);
			}
		}
		return Integer.valueOf(sb.toString());
	}
	
}
