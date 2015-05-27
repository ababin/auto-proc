package ru.babin.autoproc.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequester {
	
	private static final Logger log = LoggerFactory.getLogger(HttpRequester.class);
	
	private static final int CONNECTION_TIMEOUT = 30000;
	
	public Response request(String url){
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			client.getParams().setParameter("http.connection.timeout", CONNECTION_TIMEOUT);
			client.executeMethod(getMethod);
			return new Response(200 , getMethod.getResponseBodyAsString());
		} catch (Exception e) {
			log.warn("Error during request: {} ({})" , url , e.getMessage() , e);
			return new Response(0);
		}
	}
	
}
