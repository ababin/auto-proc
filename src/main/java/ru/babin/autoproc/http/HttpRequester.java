package ru.babin.autoproc.http;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequester {
	
	private static final Logger log = LoggerFactory.getLogger(HttpRequester.class);
	
	private static final int CONNECTION_TIMEOUT = 30000;
	
	public Response request(String url){
		return request(url , null);
	}
	
	
	public Response request(String url, Cookie [] cookies){
		System.out.println("REQUEST:  " + url);
		HttpClient client = new HttpClient();
		
		try {
			url = encodeUrl(url);
			GetMethod getMethod = new GetMethod(url);
			if(cookies != null){
				client.getState().addCookies(cookies);
			}
			client.getParams().setParameter("http.connection.timeout", CONNECTION_TIMEOUT);
			client.executeMethod(getMethod);
			//Cookie[] cookiesResp = client.getState().getCookies();
			Header [] cookieHeaders = getMethod.getResponseHeaders("Set-Cookie");
			List <Cookie> cookiesList = new LinkedList<>();
			for(Header h : cookieHeaders){
				HeaderElement el = h.getElements()[0];
				Cookie c = new Cookie();
				c.setName(el.getName());
				c.setValue(el.getValue());
				
				// other params
				try{
					c.setDomain(h.getElements()[1].getParameterByName("domain").getValue());
				}catch(Exception e){}
				try{
					c.setPath(h.getElements()[1].getParameterByName("path").getValue());
				}catch(Exception e){}

				cookiesList.add(c);
			}
			Cookie [] ar = new Cookie[cookiesList.size()];
			return new Response(200 , getMethod.getResponseBodyAsString(), cookiesList.toArray(ar));
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Error during request: {} ({})" , url , e.getMessage() , e);
			return new Response(0);
		}
	}
	
	public String encodeUrl(String str){
		return str.replace("[", "%5B").replace("]", "%5D").replace(",", "%2C").replace(" ", "+");
	}
	
}
