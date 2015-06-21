package ru.babin.autoproc.http;

public class Response {
	public final int code;
	public final String result;
	
	public Response(int code, String result){
		this.code = code;
		this.result = result;
	}
	
	public Response(int code){
		this.code = code;
		result = null;
	}
	
	public boolean isOK(){
		return code == 200;
	}
}
