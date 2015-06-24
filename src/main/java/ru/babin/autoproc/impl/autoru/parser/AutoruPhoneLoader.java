package ru.babin.autoproc.impl.autoru.parser;

import ru.babin.autoproc.api.loader.PhoneLoader;
import ru.babin.autoproc.api.model.EParam;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.http.HttpRequester;
import ru.babin.autoproc.http.Response;

public class AutoruPhoneLoader implements PhoneLoader{

	private HttpRequester requester = new HttpRequester();
	
	@Override
	public void load(Ware ware) {
		// 1. request ADS for getting cookie
		Response resAds = requester.request(ware.getAdsUrl());
				
		String phoneURL = preparePhoneURL(ware.getAdsUrl());
		Response resp = requester.request(phoneURL, resAds.cookies);
		if(resp.isOK()){
			String phone = getPhoneFromResponse(resp.result);
			ware.addParam(EParam.PHONE, phone);
		}
	}

	private String getPhoneFromResponse(String result) {
		int index = result.indexOf("\"phone\":");
		String str = result.substring(index+8);
		String [] ar = str.split(",");
		String phone = ar[0];
		return normalizePhone(phone);
	}
	
	

	private String normalizePhone(String phone) {
		return phone.replace("\"", "").replace(" ", "").replace("-", "");
	}

	private String preparePhoneURL(String adsUrl) {
		String [] ar = adsUrl.split("/");
		// prepare url for phone
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i< ar.length - 1 ; i++){
			if(sb.length() > 0){
				sb.append("/");
			}
			sb.append(ar[i]);
		}
		sb.append("/get_phones/"+ ar[ar.length-1] + "/");
		return sb.toString();
	}
}
