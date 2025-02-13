package io.versionpulse.api.apispecifications.models;

import org.springframework.http.HttpMethod;

import lombok.Getter;

@Getter
public class MethodModel {
	HttpMethod httpMethod;
	String path;
	
	public MethodModel(HttpMethod httpMethod, String path) {
		this.httpMethod = httpMethod;
		this.path = path;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("method: "+httpMethod.name()+"\n");
		sb.append("path: "+path+"\n");
		return sb.toString();
	}
}
