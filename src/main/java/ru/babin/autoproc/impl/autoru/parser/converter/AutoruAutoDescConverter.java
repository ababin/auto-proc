package ru.babin.autoproc.impl.autoru.parser.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.babin.autoproc.api.model.AutoDesc;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.Ware;

public class AutoruAutoDescConverter {
	
	public AutoDesc convert(String colorAndBodyType, String misc){
		AutoDesc ad = new AutoDesc();
		parseColorAndBodyType(colorAndBodyType, ad);
		try{
			parseMisc(misc , ad);	
		}catch(Exception e){
			System.out.println("Error parseMisc for: " + misc);
			throw e;
		}
		
		return ad;
		
		/*
		
		String [] ar =  theRest.split(" ");
		int fuelIndex  = -1;
		for(int i=0; i < ar.length; i++){
			if(ar[i].endsWith(",")){
				fuelIndex = i;
				break;
			}
		}
		
		String fuel = null;
		if(fuelIndex >= 0){
			fuel = ar[fuelIndex].substring(0, ar[fuelIndex].length()-1);
		}
		
		String gearDrive = ar[ar.length-1];
		
		
		
		
		
		
		
		// Серебряный универсал 5 дв. 1.6 MT (84 л.с.) бензин, передний
		System.out.println(w.getParam(EParam.DESC_SHORT));
		String [] ar = w.getParam(EParam.DESC_SHORT).split(" ");
		
		// 1.
		
		
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
		*/
		
	}
	
	private void parseMisc(String misc, AutoDesc ad) {
		// 2.5hyb CVT (155 л.с.) 4WD гибрид, полный
		// 1.6 MT (84 л.с.) бензин, передний
		// MT бензин, передний
		// Electro AT (516 кВт) 4WD электро, полный
						
		String [] ar = misc.split(" ");
		
		String gearDrive = ar[ar.length-1];
		ar = Arrays.copyOfRange(ar, 0, ar.length-1);
				
		String fuel = ar[ar.length-1];
		fuel = fuel.substring(0, fuel.length()-1);
		ar = Arrays.copyOfRange(ar, 0, ar.length-1);
		
		// find horses
		int indexHorses = -1;
		for(int i = 0; i < ar.length; i++){
			if(ar[i].startsWith("(")){
				indexHorses = i;
				break;
			}
		}
		int horses = 0;
		int power = 0;
		if(indexHorses >= 0){
			if(ar[indexHorses+1].startsWith("л.с.")){
				horses = Integer.valueOf(ar[indexHorses].substring(1));
			}else{
				power = Integer.valueOf(ar[indexHorses].substring(1));
			}
			ar = Arrays.copyOfRange(ar, 0, indexHorses);
		}
		
		
		// gearbox
		String gearBox = ar[ar.length-1];
		ar = Arrays.copyOfRange(ar, 0, ar.length-1);
		
		// volume
		int volume = 0;
		if(ar.length > 0){
			volume = getAsInt(ar[0]);
		}
		
		ad.setDrivingGear(gearDrive);
		ad.setFuel(fuel);
		ad.setHorses(horses);
		ad.setPower(power);
		ad.setGearBoxType(gearBox);
		ad.setEngineVolume(volume);
		
	}
	
	private void parseColorAndBodyType(String colorAndBodyType, AutoDesc ad) {
		// Черный внедорожник 5 дв.
		
		String [] ar = colorAndBodyType.split(" ");
		String color = ar[0];
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < ar.length; i++){
			sb.append(" " + ar[i]);
		}
		ad.setColor(color.toLowerCase());
		ad.setBodyType(sb.toString().trim().toLowerCase());
	}

	private int getAsInt(String str){
		StringBuilder sb = new StringBuilder();
		for(char c : str.toCharArray()){
			if("0123456789".indexOf(c) >= 0){
				sb.append(c);
			}
		}
		return sb.length() == 0 ? 0 : Integer.valueOf(sb.toString());
	}
	
}
