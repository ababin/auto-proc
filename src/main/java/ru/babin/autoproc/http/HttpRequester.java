package ru.babin.autoproc.http;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
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
			getMethod.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			setCookies(cookies, client);
			getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64)");
			client.getParams().setParameter("http.connection.timeout", CONNECTION_TIMEOUT);
			client.executeMethod(getMethod);
			return new Response(200 , getMethod.getResponseBodyAsString(), extractCookies(getMethod));
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Error during request: {} ({})" , url , e.getMessage() , e);
			return new Response(0);
		}
	}

	private void setCookies(Cookie[] cookies, HttpClient client) {
		if(cookies != null){
			client.getState().addCookies(cookies);
		}
	}

	private Cookie[] extractCookies(GetMethod getMethod) {
		Header[] cookieHeaders = getMethod.getResponseHeaders("Set-Cookie");;
		List <Cookie> cookiesList = new LinkedList<>();
		for(Header h : cookieHeaders){
			HeaderElement el = h.getElements()[0];
			if("autoru_sid".equals(el.getName())){
				Cookie c = new Cookie();
				c.setName(el.getName());
				c.setValue(el.getValue());
				c.setDomain("auto.ru");
				
				
				try{
					c.setPath(h.getElements()[1].getParameterByName("path").getValue());
				}catch(Exception e){}
								
				c.setExpiryDate(new Date(System.currentTimeMillis() + 86400000L));
				cookiesList.add(c);
			}
		}
		Cookie [] ar = new Cookie[cookiesList.size()];
		ar = cookiesList.toArray(ar);
		return ar;
	}
	
	public String encodeUrl(String str){
		return str.replace("[", "%5B").replace("]", "%5D").replace(",", "%2C").replace(" ", "+");
	}
	
}
