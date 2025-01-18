package io.versionpulse.api.apispecifications.models;

import java.util.List;

import lombok.Getter;

public class ParameterModel {
	List<QueryString> queryString;
	List<RequestParameter> requestParameter;
	RequestBody requestBody;
	
	public ParameterModel(List<QueryString> queryString, List<RequestParameter> requestParameter, RequestBody requestBody) {
		this.queryString = queryString;
		this.requestParameter = requestParameter;
		this.requestBody = requestBody;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (queryString.size() > 0) {
			sb.append("queryString\n");			
		}
		for (QueryString item : queryString) {
			sb.append(item.getType()+": "+item.getName()+"\n");
		}
		if (requestParameter.size() > 0) {
			sb.append("requestParameter\n");			
		}
		for (RequestParameter item : requestParameter) {
			sb.append(item.getType()+": "+item.getName()+"\n");
		}
		if (this.requestBody != null) {
			sb.append("requestBody\n");
			sb.append(requestBody.getBody().getName());
		}
		return sb.toString();
	}
	
	@Getter
	public static class QueryString {
		String type;
		String name;
		
		public QueryString(String type, String name) {
			this.type = type;
			this.name = name;
		}
	}
	
	@Getter
	public static class RequestParameter {
		String type;
		String name;
		
		public RequestParameter(String type, String name) {
			this.type = type;
			this.name = name;
		}
	}
	
	@Getter
	public static class RequestBody {
		Class<?> body;
		
		public RequestBody(Class<?> body) {
			this.body = body;
		}
	}
}
